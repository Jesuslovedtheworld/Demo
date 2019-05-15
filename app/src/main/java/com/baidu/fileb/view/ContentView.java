package com.baidu.fileb.view;

import com.baidu.fileb.bean.ListBean;

public interface ContentView {
    void onSuccess(ListBean bean);

    void onFile(String s);
}
