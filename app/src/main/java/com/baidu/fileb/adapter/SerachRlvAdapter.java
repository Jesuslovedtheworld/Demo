package com.baidu.fileb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.fileb.R;
import com.baidu.fileb.bean.BannerBean;
import com.baidu.fileb.bean.SenrceBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SerachRlvAdapter extends RecyclerView.Adapter <SerachRlvAdapter.VH>{
    private static final String TAG = "SerachRlvAdapter";
        private ArrayList<SenrceBean.NewslistBean> arr;
        private Context context;

    public void setArr(ArrayList<SenrceBean.NewslistBean> arr) {
        this.arr = arr;
    }

    public SerachRlvAdapter(ArrayList<SenrceBean.NewslistBean> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.searchitem, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        SenrceBean.NewslistBean bean = arr.get(i);
        if (bean != null){
            vh.title.setText(bean.getTitle());
            vh.smalltv.setText(bean.getCtime()+bean.getDescription());
            Glide.with(context).load(bean.getPicUrl()).into(vh.img);
        }

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView smalltv;
        private final ImageView img;

        public VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            smalltv = itemView.findViewById(R.id.smalltv);
            img = itemView.findViewById(R.id.img);
        }
    }
}
