package com.baidu.fileb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.fileb.R;
import com.baidu.fileb.adapter.SerachRlvAdapter;
import com.baidu.fileb.api.HomeApi;
import com.baidu.fileb.bean.BannerBean;
import com.baidu.fileb.bean.SenrceBean;
import com.baidu.fileb.model.HomeModelImpi;
import com.baidu.fileb.persenter.ContentBannerPersenter;
import com.baidu.fileb.view.BannerView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "HomeModelImpi";
    /**
     * 搜索
     */
    private TextView mTitle;
    private Toolbar mToolbar;
    private RecyclerView mRe;
    private ArrayList<SenrceBean.NewslistBean> arr;
    private SerachRlvAdapter adapter;
    private String stringContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
    }

    private void initData() {
        Log.e(TAG, "传过来的信息: "+stringContent);
        if (stringContent != null){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeApi.seanceUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HomeApi api = retrofit.create(HomeApi.class);
        Observable<SenrceBean> data = api.getSenrceData(stringContent);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SenrceBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SenrceBean senrceBean) {
                            if (senrceBean != null){
                                    arr.addAll(senrceBean.getNewslist());
                                    adapter.setArr(arr);
                                    adapter.notifyDataSetChanged();
                            }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        }
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "方法走了 " );
                finish();
            }
        });
        setSupportActionBar(mToolbar);
        mRe = (RecyclerView) findViewById(R.id.re);
        mRe.setLayoutManager(new LinearLayoutManager(this));
        arr = new ArrayList<>();
        adapter = new SerachRlvAdapter(arr, this);
        mRe.setAdapter(adapter);
        Intent intent = getIntent();
        stringContent = intent.getStringExtra("string");
    }

}
