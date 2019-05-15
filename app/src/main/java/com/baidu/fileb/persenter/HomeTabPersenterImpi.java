package com.baidu.fileb.persenter;

import com.baidu.fileb.bean.TabBean;
import com.baidu.fileb.callback.ResultCallBack;
import com.baidu.fileb.model.HomeModel;
import com.baidu.fileb.view.HomeTabView;

public class HomeTabPersenterImpi implements HomeTabPersenter, ResultCallBack<TabBean> {
    private HomeModel model;
    private HomeTabView homeTabView;

    public HomeTabPersenterImpi(HomeModel model, HomeTabView homeTabView) {
        this.model = model;
        this.homeTabView = homeTabView;
    }

    @Override
    public void getData() {
        if (model != null){
            model.getTabData(this);
        }
    }

    @Override
    public void getContentData(int id) {

    }

    @Override
    public void onSuccess(TabBean bean) {
            if (homeTabView != null){
                homeTabView.onSuccess(bean);
            }
    }

    @Override
    public void onFile(String s) {
            if (homeTabView != null){
                homeTabView.onFile(s);
            }
    }
}
