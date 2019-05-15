package com.baidu.fileb.persenter;

import com.baidu.fileb.bean.BannerBean;
import com.baidu.fileb.bean.ListBean;
import com.baidu.fileb.callback.ResultCallBack;
import com.baidu.fileb.model.HomeModel;
import com.baidu.fileb.view.BannerView;
import com.baidu.fileb.view.ContentView;

public class ContentListPersenter implements HomeTabPersenter, ResultCallBack<ListBean> {
    private HomeModel model;
    private ContentView view;

    public ContentListPersenter(HomeModel model, ContentView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getData() {

    }

    @Override
    public void getContentData(int id) {
        if (model != null){
            model.getListData(id,this);
        }
    }

    @Override
    public void onSuccess(ListBean bean) {
            if (view != null){
                view.onSuccess(bean);
            }
    }

    @Override
    public void onFile(String s) {
            if (view != null){
                view.onFile(s);
            }
    }
}
