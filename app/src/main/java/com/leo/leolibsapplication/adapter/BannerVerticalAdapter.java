package com.leo.leolibsapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leo.leolibsapplication.R;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class BannerVerticalAdapter extends BannerAdapter {


    private Context context;
    private List<String> datas;

    public BannerVerticalAdapter(Context context, List<String> datas) {
        super(datas);
        this.context = context;
        this.datas = datas;
    }

    @Override
    public Object onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adt_xrecyclerview_item, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindView(Object holder, Object data, int position, int size) {
        BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
        bannerViewHolder.textView.setSelected(true);
        bannerViewHolder.textView.setText(datas.get(position));
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public BannerViewHolder(@NonNull View view) {
            super(view);
            this.textView = view.findViewById(R.id.adt_text);

        }
    }
}
