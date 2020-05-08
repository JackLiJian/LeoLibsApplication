package com.leo.leolibsapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.leo.leolibrary.utils.GlideCircleBorderTransform;
import com.leo.leolibsapplication.R;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MzBannerActivity extends AppCompatActivity {


    @BindView(R.id.banner)
    MZBannerView mzBannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mz_banner);
        ButterKnife.bind(this);
        List<String> data = new ArrayList<>();
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587102883715&di=01cc38e414c36ffefdb152865cf7ee80&imgtype=0&src=http%3A%2F%2Fbbs.jooyoo.net%2Fattachment%2FMon_0905%2F24_65548_2835f8eaa933ff6.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587102883714&di=b22646fd5989ddf1e0811c5c1dd5adf7&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587102883714&di=b22646fd5989ddf1e0811c5c1dd5adf7&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587102883714&di=b22646fd5989ddf1e0811c5c1dd5adf7&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg");

        // 设置数据
        mzBannerView.setPages(data, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        //设置BannerView 的切换时间间隔
        mzBannerView.setDelayedTime(3000);
        mzBannerView.setIndicatorVisible(false);
        mzBannerView.start();
    }


    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.item_image_view, null);
            imageView = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)
                    .bitmapTransform(new GlideCircleBorderTransform(20, R.color.color_000000));

            // 数据绑定
            Glide.with(context)
                    .load(data)
                    .apply(requestOptions)
//                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageView);
        }
    }
}
