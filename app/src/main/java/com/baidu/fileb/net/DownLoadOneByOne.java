package com.baidu.fileb.net;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class DownLoadOneByOne extends HandlerThread {
    //结合HandlerThread 串行的执行单个耗时任务，入单任务下载
    public DownLoadOneByOne(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        //初始化下载组件
    }
    private Handler jihandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String url = (String) msg.obj;

        }
    };
}
