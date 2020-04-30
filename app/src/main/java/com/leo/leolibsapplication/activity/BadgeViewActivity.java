package com.leo.leolibsapplication.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.leo.leolibsapplication.R;

import q.rorbin.badgeview.QBadgeView;

public class BadgeViewActivity extends AppCompatActivity {

    private TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_badge_view);
        textView = findViewById(R.id.text);
//        new QBadgeView(this).bindTarget(textView).setBadgeNumber(5);
        new QBadgeView(this).bindTarget(textView).setBadgeText("");
    }
}
