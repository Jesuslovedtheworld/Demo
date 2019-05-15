package com.baidu.fileb.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.fileb.R;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "HandlerActivity";
    private Button btn;
    Handler.Callback mCallBack = new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            //该接口的实现就是处异步耗时操作任务的，因此该方法执行在子线程中
            Log.d(TAG, "子线程的名字" + Thread.currentThread().getName());
            return false;
        }
    };
    private HandlerThread mWorkThread;
    private Handler handler;
    private Handler mSubHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        initView();
        //获取现在线程的名字
        Log.d(TAG, "onCreate: " + Thread.currentThread().getName());

        //创建HandlerThread工作线程
        mWorkThread = new HandlerThread("workHandlerThread");
        mWorkThread.start();

        //获取Handler的Lopper
        Looper looper = mWorkThread.getLooper();
        //创建Handler,通过Lopper初始化  通过接口回调
        mSubHandler = new Handler(looper, mCallBack);


        //将异步任务耗时任务投放到HandlerThread中
        Message message = Message.obtain();

    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                mSubHandler.sendEmptyMessage(0);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWorkThread != null) {
            mWorkThread.quit();
        }
    }



}

