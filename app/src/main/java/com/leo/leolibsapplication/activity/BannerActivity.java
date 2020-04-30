package com.leo.leolibsapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leo.leolibsapplication.R;
import com.leo.leolibsapplication.adapter.BannerVerticalAdapter;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnPageChangeListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {

    private Banner banner;
    private RecyclerView recyclerView;

    private List<String> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_banner);
        banner = findViewById(R.id.act_banner);
        recyclerView = findViewById(R.id.recyclerView);
        setData();
        setBanner();
    }

    private void setData() {
        data = new ArrayList<>();
//        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587102883715&di=01cc38e414c36ffefdb152865cf7ee80&imgtype=0&src=http%3A%2F%2Fbbs.jooyoo.net%2Fattachment%2FMon_0905%2F24_65548_2835f8eaa933ff6.jpg");
//        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587102883714&di=b22646fd5989ddf1e0811c5c1dd5adf7&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg");
//        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587102883714&di=b22646fd5989ddf1e0811c5c1dd5adf7&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg");
//        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587102883714&di=b22646fd5989ddf1e0811c5c1dd5adf7&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg");
        data.add("123春眠不觉晓+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        data.add("123处处闻啼鸟+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        data.add("123夜来风雨声+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        data.add("123花落知多少+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    private void setBanner() {
//        banner.setAdapter(new ImageAdapter(this, data))
//                .setIndicator(new CircleIndicator(this))
//                .setIndicatorSelectedColorRes(R.color.colorAccent)
//                .setIndicatorNormalColorRes(R.color.colorPrimary)
//                .setIndicatorGravity(IndicatorConfig.Direction.LEFT)
//                .setIndicatorSpace((int) BannerUtils.dp2px(20))
//                .setIndicatorMargins(new IndicatorConfig.Margins((int) BannerUtils.dp2px(10)))
//                .setIndicatorWidth(10, 20)
//                .addItemDecoration(new MarginDecoration((int) BannerUtils.dp2px(50)))
//                .setIndicator(new CircleIndicator(this))
//                .setIndicatorRadius(0)
//                .setBannerRound(50)
//                .setBannerGalleryEffect(20, 20, 20)
//                .start();

        banner.getViewPager2().setOffscreenPageLimit(5);
        banner.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("VIEW", "position=" + position);
                if (position==2){
                    banner.getAdapter().getViewHolder().itemView.invalidate();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        banner.setAdapter(new BannerVerticalAdapter(this, data))
                .setOrientation(Banner.VERTICAL)
                .start();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommonAdapter<String>(this, R.layout.adt_xrecyclerview_item, data) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.adt_text, s);
                TextView textView = holder.getView(R.id.adt_text);
                textView.setSelected(true);
            }
        });

    }
}
