package com.leo.leolibsapplication.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.leo.leolibsapplication.R;
import com.leo.leolibsapplication.adapter.NewLazyAdapter;
import com.leo.leolibsapplication.fragment.NewLazyFirstFragment;
import com.leo.leolibsapplication.fragment.NewLazySecondFragment;
import com.leo.leolibsapplication.fragment.NewLazyThirdFragment;

import java.util.ArrayList;
import java.util.List;

public class NewLazyActivity extends AppCompatActivity {


    private ViewPager2 viewPager;
    private NewLazyAdapter newLazyAdapter;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_new_lazy);
        viewPager = findViewById(R.id.act_new_lazy_vp);
        setFragments();
        setViewPager();
    }


    private void setViewPager() {
        newLazyAdapter = new NewLazyAdapter(this, fragments);
        viewPager.setAdapter(newLazyAdapter);
    }


    private void setFragments() {
        fragments = new ArrayList<>();
        fragments.add(new NewLazyFirstFragment());
        fragments.add(new NewLazySecondFragment());
        fragments.add(new NewLazyThirdFragment());
    }

}
