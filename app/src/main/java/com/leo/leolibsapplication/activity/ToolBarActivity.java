package com.leo.leolibsapplication.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.leo.leolibsapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolBarActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tool_bar);
        ButterKnife.bind(this);
//        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("dasdas");
        setToolBar();
    }


    private void setToolBar() {
//        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu);
    }

}
