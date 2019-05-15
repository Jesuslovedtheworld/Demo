package com.baidu.fileb.persenter;

import com.baidu.fileb.bean.BannerBean;
import com.baidu.fileb.callback.ResultCallBack;
import com.baidu.fileb.model.HomeModel;
import com.baidu.fileb.view.BannerView;
import com.baidu.fileb.view.ContentView;

public class ContentBannerPersenter implements HomeTabPersenter, ResultCallBack<BannerBean> {
    private HomeModel model;
    private BannerView bannerView;

    public ContentBannerPersenter(HomeModel model, BannerView bannerView) {
        this.model = model;
        this.bannerView = bannerView;
    }
    @Override
    public void getData() {
            if (model != null){
                model.getBannerData(this);
            }
    }

    @Override
    public void getContentData(int id) {

    }

    @Override
    public void onSuccess(BannerBean bean) {
                if (bannerView != null){
                    bannerView.onSuccess(bean);
                }
    }

    @Override
    public void onFile(String s) {
            if (bannerView != null){
                bannerView.onFile(s);
            }
    }
}
