package com.baidu.fileb.model;

import com.baidu.fileb.bean.BannerBean;
import com.baidu.fileb.bean.ListBean;
import com.baidu.fileb.bean.TabBean;
import com.baidu.fileb.callback.ResultCallBack;

public interface HomeModel {
    void getBannerData(ResultCallBack<BannerBean> resultCallBack);
    void getTabData(ResultCallBack<TabBean> resultCallBack);
    void getListData(int id,ResultCallBack<ListBean> resultCallBack);

}
