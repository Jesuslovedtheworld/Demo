package com.baidu.fileb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.baidu.fileb.R;

public class MainActivity extends AppCompatActivity {

    /**
     * 看车
     */
    private TextView one;
    /**
     * 选车
     */
    private TextView two;
    /**
     * 买车
     */
    private TextView three;
    /**
     * 聊车
     */
    private TextView four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        scale();
    }

    private void scale() {
        ScaleAnimation animation = new ScaleAnimation(0.0f, 3.0f, 0.0f, 3.0f);
        animation.setDuration(3000);
        one.startAnimation(animation);
        two.startAnimation(animation);
        three.startAnimation(animation);
        four.startAnimation(animation);
        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
               startActivity(new Intent(MainActivity.this,HomeActivity.class));
            }
        }.start();
    }

    private void initView() {
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        four = (TextView) findViewById(R.id.four);
    }
}
