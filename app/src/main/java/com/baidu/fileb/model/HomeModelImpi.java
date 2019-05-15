package com.baidu.fileb.model;

import android.util.Log;

import com.baidu.fileb.api.HomeApi;
import com.baidu.fileb.bean.BannerBean;
import com.baidu.fileb.bean.ListBean;
import com.baidu.fileb.bean.TabBean;
import com.baidu.fileb.callback.ResultCallBack;
import com.baidu.fileb.net.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModelImpi implements HomeModel {
    private static final String TAG = "HomeModelImpi";
    @Override
    public void getBannerData(final ResultCallBack<BannerBean> resultCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeApi.bannerUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HomeApi api = retrofit.create(HomeApi.class);
        Observable<BannerBean> bean = api.getBannerBean();
        bean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        Log.d(TAG, "获取的信息: "+bannerBean.toString());
                            if (bannerBean != null){
                                resultCallBack.onSuccess(bannerBean);
                            }else {
                                resultCallBack.onFile("获取数据失败");
                            }
                    }

                    @Override
                    public void onError(Throwable e) {
                        resultCallBack.onFile(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getTabData(final ResultCallBack<TabBean> resultCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeApi.tabListUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HomeApi api = retrofit.create(HomeApi.class);
        Observable<TabBean> tabBean = api.getTabBean();
        tabBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabBean tabBean) {
                            if (tabBean != null){
                                resultCallBack.onSuccess(tabBean);
                            }else {
                                resultCallBack.onFile("信息错误");
                            }
                    }

                    @Override
                    public void onError(Throwable e) {
                            resultCallBack.onFile(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getListData(int id, final ResultCallBack<ListBean> resultCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeApi.tabListUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HomeApi api = retrofit.create(HomeApi.class);
        Observable<ListBean> listBean = api.getListBean(id);
        listBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        if (listBean != null){
                            resultCallBack.onSuccess(listBean);
                        }else {
                            resultCallBack.onFile("信息失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        resultCallBack.onFile(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
