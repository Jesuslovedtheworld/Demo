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
import com.baidu.fileb.bean.ListBean;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class ReuseRlvAdapter extends RecyclerView.Adapter{
    private ArrayList<BannerBean.ResultsBean> banners;
    private ArrayList<ListBean.DataBean.DatasBean> contentLists;
    private Context context;
    private int TYPE_BANNER = 1;
    private int TYPE_CONTENT = 2;
    private static final String TAG = "ReuseRlvAdapter";
    private int mPosition;

    public void setBanners(ArrayList<BannerBean.ResultsBean> banners) {
        this.banners = banners;
    }

    public void setContentLists(ArrayList<ListBean.DataBean.DatasBean> contentLists) {
        this.contentLists = contentLists;
    }

  public ReuseRlvAdapter(ArrayList<BannerBean.ResultsBean> banners, ArrayList<ListBean.DataBean.DatasBean> contentLists, Context context) {
        this.banners = banners;
        this.contentLists = contentLists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder holder = null;
        int count = viewGroup.getChildCount();
        Log.e(TAG, "onCreateViewHolderviewType: "+viewType );
        Log.e(TAG, "onCreateViewHolder: "+count );
        if (viewType == TYPE_BANNER){
            View view = LayoutInflater.from(context).inflate(R.layout.banneritem, null);
            holder = new BannerVH(view);
        }
        if (viewType == TYPE_CONTENT){
            View view = LayoutInflater.from(context).inflate(R.layout.contentitem, null);
            holder = new ListVH(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof BannerVH){
            BannerVH bannerVH = (BannerVH) viewHolder;
            bannerVH.banner.setImages(banners);
            bannerVH.banner.setImageLoader(new ImageLoaderVH());
            bannerVH.banner.start();
        }
        if (viewHolder instanceof ListVH){
            ListVH listVH = (ListVH) viewHolder;
            if (banners.size() > 0){
                mPosition = i - 1;
            }
            final ListBean.DataBean.DatasBean bean = contentLists.get(mPosition);
            String desc = bean.getDesc();
            if (desc != null){
                listVH.tv.setText(desc);
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myItemOnListener != null){
                        myItemOnListener.myListener(bean);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (banners.size() > 0){
            return contentLists.size() +1;
        }
        return contentLists.size();
    }
    class BannerVH extends RecyclerView.ViewHolder{

        private final Banner banner;

        public BannerVH(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_BANNER;
        }
        return TYPE_CONTENT;
    }

    class ListVH extends RecyclerView.ViewHolder{

        private final TextView tv;

        public ListVH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
    class ImageLoaderVH extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            BannerBean.ResultsBean bean = (BannerBean.ResultsBean) path;
            String url = bean.getUrl();
            if (url != null){
                Glide.with(context).load(url).into(imageView);
            }
        }
    }
    public interface MyItemOnListener{
        void myListener( ListBean.DataBean.DatasBean bean);
    }
    private MyItemOnListener myItemOnListener;

    public void setMyItemOnListener(MyItemOnListener myItemOnListener) {
        this.myItemOnListener = myItemOnListener;
    }
}
