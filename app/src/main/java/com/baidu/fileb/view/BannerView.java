package com.baidu.fileb.view;

import com.baidu.fileb.bean.BannerBean;

public interface BannerView {
    void onSuccess(BannerBean bean);

    void onFile(String s);
}
