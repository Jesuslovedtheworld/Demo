package com.baidu.fileb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.baidu.fileb.R;
import com.baidu.fileb.callback.TabTouchCallBack;

import java.util.ArrayList;
import java.util.Collections;


public class TabSelectRlvAdapter extends RecyclerView.Adapter<TabSelectRlvAdapter.VH>implements TabTouchCallBack {
    private ArrayList<String> titles;
    private Context context;

    public TabSelectRlvAdapter(ArrayList<String> titles, Context context) {
        this.titles = titles;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabbuttonitem, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        String s = titles.get(i);
        if (s != null){
            vh.btn.setText(s);
        }
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    @Override
    public boolean onItemExChange(int adapterPosition, int adapterPosition1) {
        Collections.swap(titles,adapterPosition,adapterPosition1);
        notifyItemMoved(adapterPosition,adapterPosition1);
        return false;
    }

    @Override
    public void onItemDelete(int adapterPosition) {
        titles.remove(adapterPosition);
        notifyItemChanged(adapterPosition);
    }

    public class VH extends RecyclerView.ViewHolder {

        private final Button btn;

        public VH(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
        }
    }
}
