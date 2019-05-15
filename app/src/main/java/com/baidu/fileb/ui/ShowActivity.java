package com.baidu.fileb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.fileb.R;
import com.baidu.fileb.bean.CallBean;
import com.bumptech.glide.Glide;

import java.io.Serializable;

public class ShowActivity extends AppCompatActivity {

    private TextView mDesc;
    private ImageView mSmallImage;
    private TextView mAuthor;
    private TextView mTime;
    private TextView mContent;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        CallBean callbean = (CallBean) intent.getSerializableExtra("callbean");
        if (callbean != null){
        mDesc.setText(callbean.getDesc());
        String image = callbean.getImage();
        if (image != null){
            Glide.with(this).load(image).into(mSmallImage);
            Glide.with(this).load(image).into(mImage);
        }
        mAuthor.setText(callbean.getName());
        mTime.setText(callbean.getTime());
        mContent.setText(callbean.getDesc());
        }

    }

    private void initView() {
        mDesc = (TextView) findViewById(R.id.desc);
        mSmallImage = (ImageView) findViewById(R.id.smallImage);
        mAuthor = (TextView) findViewById(R.id.author);
        mTime = (TextView) findViewById(R.id.time);
        mContent = (TextView) findViewById(R.id.content);
        mImage = (ImageView) findViewById(R.id.image);
    }
}
