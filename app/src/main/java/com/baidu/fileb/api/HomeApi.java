package com.baidu.fileb.api;

import com.baidu.fileb.bean.BannerBean;
import com.baidu.fileb.bean.ListBean;
import com.baidu.fileb.bean.SenrceBean;
import com.baidu.fileb.bean.TabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeApi {
    String bannerUrl = "http://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/10/1")
    Observable<BannerBean> getBannerBean();

    String tabListUrl = "https://www.wanandroid.com/project/";
    @GET("tree/json")
    Observable<TabBean> getTabBean();


    @GET("list/1/json?")
    Observable<ListBean> getListBean(@Query("cid") int cid);

    String seanceUrl = "http://api.tianapi.com/wxnew/";
    @GET("?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=10&")
    Observable<SenrceBean> getSenrceData(@Query("word")String word);

}
