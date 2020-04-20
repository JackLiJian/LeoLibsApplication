package com.leo.leolibsapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.leo.leolibsapplication.R;
import com.leo.leolibsapplication.adapter.ImageAdapter;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.itemdecoration.MarginDecoration;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BannerActivity extends AppCompatActivity {

    private Banner banner;

    private List<String> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_banner);
        banner = findViewById(R.id.act_banner);
        setData();
        setBanner();
    }

    private void setData() {
        data = new ArrayList<>();
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587102883715&di=01cc38e414c36ffefdb152865cf7ee80&imgtype=0&src=http%3A%2F%2Fbbs.jooyoo.net%2Fattachment%2FMon_0905%2F24_65548_2835f8eaa933ff6.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587102883714&di=b22646fd5989ddf1e0811c5c1dd5adf7&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg");
    }

    private void setBanner() {
        banner.setAdapter(new ImageAdapter(this, data))
                .setIndicator(new CircleIndicator(this))
                .setIndicatorSelectedColorRes(R.color.colorAccent)
                .setIndicatorNormalColorRes(R.color.colorPrimary)
                .setIndicatorGravity(IndicatorConfig.Direction.LEFT)
                .setIndicatorSpace((int) BannerUtils.dp2px(20))
                .setIndicatorMargins(new IndicatorConfig.Margins((int) BannerUtils.dp2px(10)))
                .setIndicatorWidth(10, 20)
                .addItemDecoration(new MarginDecoration((int) BannerUtils.dp2px(50)))
                .setIndicator(new CircleIndicator(this))
                .setIndicatorRadius(0)
                .setBannerRound(50)
                .setBannerGalleryEffect(20, 20, 20)
                .start();

    }
}
