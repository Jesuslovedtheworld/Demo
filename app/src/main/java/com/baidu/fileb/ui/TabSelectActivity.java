package com.baidu.fileb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.baidu.fileb.R;
import com.baidu.fileb.adapter.TabSelectRlvAdapter;
import com.baidu.fileb.callback.SimpleTouchHelperCallBack;

import java.util.ArrayList;

public class TabSelectActivity extends AppCompatActivity {

    private RecyclerView re;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_select);
        initView();
        initData();
    }

    private void initData() {
        re.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        if (list != null){
            TabSelectRlvAdapter adapter = new TabSelectRlvAdapter(list, this);
            re.setAdapter(adapter);
            re.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            //托转移动和侧滑删除的功能
            SimpleTouchHelperCallBack back =new SimpleTouchHelperCallBack(adapter);
            back.setmSwipeEnable(false);
            ItemTouchHelper helper =new ItemTouchHelper(back);
            helper.attachToRecyclerView(re);
        }

    }

    private void initView() {
        re = (RecyclerView) findViewById(R.id.re);
        Intent intent = getIntent();
        list = intent.getStringArrayListExtra("list");
    }

}
