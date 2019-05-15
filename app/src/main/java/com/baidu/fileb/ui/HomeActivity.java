package com.baidu.fileb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.baidu.fileb.R;
import com.baidu.fileb.adapter.HomeVpAdapter;
import com.baidu.fileb.api.HomeApi;
import com.baidu.fileb.bean.TabBean;
import com.baidu.fileb.fragment.ReuseFragment;
import com.baidu.fileb.model.HomeModelImpi;
import com.baidu.fileb.persenter.HomeTabPersenterImpi;
import com.baidu.fileb.view.HomeTabView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements HomeTabView, View.OnClickListener {
    private static final String TAG = "HomeActivity";
    private TabLayout mTab;
    private ViewPager mVp;
    private Toolbar mToolbar;
    private MaterialSearchView mSearchView;
    private FrameLayout mToolbarContainer;
    private String queryString;
    private int listIndex = 0;
    private ArrayList<TabBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initData() {
        HomeTabPersenterImpi impi = new HomeTabPersenterImpi(new HomeModelImpi(), this);
        impi.getData();
    }

    private void initView() {
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_loading_rotate);
        mToolbar.setNavigationOnClickListener(this);
        mSearchView = (MaterialSearchView) findViewById(R.id.search_view);
        mToolbarContainer = (FrameLayout) findViewById(R.id.toolbar_container);
        mSearchView.setVoiceSearch(false);

    }

    private void seacherListener() {
        mSearchView.setCursorDrawable(R.drawable.color_cursor_white);
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e(TAG, "onQueryTextSubmit: "+query);
                //点击搜索按钮是 query为搜索框里的内容
                queryString = query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e(TAG, "onQueryTextChange: "+newText );
                //点击条目框方法走动
                //当输入搜索内容的时候 newText为搜索内容
                return false;
            }
        });
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                Log.e(TAG, "onSearchViewShown: ");
                //点击条目框方法走动
            }

            @Override
            public void onSearchViewClosed() {
                Log.e(TAG, "onSearchViewClosed: ");
                //关闭走
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                intent.putExtra("string",queryString);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onFile(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(TabBean bean) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        if (bean != null) {
            data = (ArrayList<TabBean.DataBean>) bean.getData();
            for (TabBean.DataBean datum : data) {
                ReuseFragment fragment = new ReuseFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id",datum.getId());
                fragment.setArguments(bundle);
                fragments.add(fragment);
            }
            HomeVpAdapter adapter = new HomeVpAdapter(getSupportFragmentManager(), fragments, data);
            mVp.setAdapter(adapter);
            mTab.setupWithViewPager(mVp);
            if (listIndex == 0){
                seacherListener();
                listIndex++;
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                mSearchView.showSearch(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(HomeActivity.this, TabSelectActivity.class);
        ArrayList<String> titles = new ArrayList<>();
        if (data != null){
            for (TabBean.DataBean datum : data) {
                titles.add(datum.getName());
            }
            intent.putExtra("list",titles);
            startActivity(intent);
        }

    }
    /**
     * 点击返回键的时候会调用
     */
    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

}
