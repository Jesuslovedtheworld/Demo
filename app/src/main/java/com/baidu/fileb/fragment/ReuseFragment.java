package com.baidu.fileb.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.fileb.R;
import com.baidu.fileb.adapter.ReuseRlvAdapter;
import com.baidu.fileb.bean.BannerBean;
import com.baidu.fileb.bean.CallBean;
import com.baidu.fileb.bean.ListBean;
import com.baidu.fileb.model.HomeModelImpi;
import com.baidu.fileb.persenter.ContentBannerPersenter;
import com.baidu.fileb.persenter.ContentListPersenter;
import com.baidu.fileb.ui.ShowActivity;
import com.baidu.fileb.view.BannerView;
import com.baidu.fileb.view.ContentView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReuseFragment extends Fragment implements BannerView, ContentView, ReuseRlvAdapter.MyItemOnListener {
    private static final String TAG = "ReuseFragment";
    private int mid;
    private View view;
    private RecyclerView mRe;
    private ArrayList<BannerBean.ResultsBean> bannerList;
    private ArrayList<ListBean.DataBean.DatasBean> contentList;
    private ReuseRlvAdapter reuseRlvAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reuse, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        ContentBannerPersenter persenter = new ContentBannerPersenter(new HomeModelImpi(), this);
        persenter.getData();
        Bundle bundle = getArguments();
        mid = bundle.getInt("id");
    }

    private void initView(View view) {
        mRe = (RecyclerView) view.findViewById(R.id.re);
        mRe.setLayoutManager(new LinearLayoutManager(getContext()));
        bannerList = new ArrayList<>();
        contentList = new ArrayList<>();
        reuseRlvAdapter = new ReuseRlvAdapter(bannerList, contentList, getContext());
        mRe.setAdapter(reuseRlvAdapter);
        reuseRlvAdapter.setMyItemOnListener(this);
    }

    @Override
    public void onSuccess(BannerBean bean) {
            if (bean != null){
                Log.e(TAG, "BannerBean: "+bean);
                bannerList.addAll(bean.getResults());
                reuseRlvAdapter.setBanners(bannerList);
                reuseRlvAdapter.notifyDataSetChanged();
                ContentListPersenter contentListPersenter = new ContentListPersenter(new HomeModelImpi(), this);
                contentListPersenter.getContentData(mid);
            }
    }

    @Override
    public void onSuccess(ListBean bean) {
        if (bean != null){
            Log.e(TAG, "ListBean: "+bean);
            contentList.addAll(bean.getData().getDatas());
            reuseRlvAdapter.setContentLists(contentList);
            reuseRlvAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFile(String s) {
        Toast.makeText(getContext(),""+s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void myListener(ListBean.DataBean.DatasBean bean) {//点击监听
        Intent intent = new Intent(getContext(), ShowActivity.class);
        CallBean callBean = new CallBean(bean.getEnvelopePic(), bean.getTitle(), bean.getAuthor(), bean.getDesc(),bean.getNiceDate());
        intent.putExtra("callbean",callBean);
        startActivity(intent);
    }
}
