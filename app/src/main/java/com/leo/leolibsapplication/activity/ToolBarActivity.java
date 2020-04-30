package com.leo.leolibsapplication.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.leo.leolibsapplication.R;

public class ToolBarActivity extends AppCompatActivity {


    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tool_bar);
        setToolBar();
    }


    private void setToolBar() {
        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu);
    }

}
