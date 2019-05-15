package com.baidu.fileb.callback;

public interface ResultCallBack<T> {
        void onSuccess(T bean);
        void onFile(String s);
}
