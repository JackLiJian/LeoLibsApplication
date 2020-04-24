package com.leo.leolibsapplication.activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;


public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new XPopup.Builder(this).asConfirm("我是标题", "我是内容",
                new OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        Toast.makeText(DialogActivity.this, "lalalalala", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
