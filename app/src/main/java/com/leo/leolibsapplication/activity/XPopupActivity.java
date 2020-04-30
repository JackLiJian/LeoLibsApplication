package com.leo.leolibsapplication.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.leo.leolibsapplication.R;
import com.leo.leolibsapplication.views.CustomAttachPopupView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

public class XPopupActivity extends AppCompatActivity {


    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_xpopup);

        button = findViewById(R.id.button);

//        new XPopup.Builder(this)
//                .atView(button)  // 依附于所点击的View，内部会自动判断在上方或者下方显示
//                .asAttachList(new String[]{"分享", "编辑", "不带icon"},
//                        new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher},
//                        new OnSelectListener() {
//                            @Override
//                            public void onSelect(int position, String text) {
//                                Toast.makeText(XPopupActivity.this, "click", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                .show();
//
        new XPopup.Builder(this).atView(button).hasShadowBg(false).asCustom(new CustomAttachPopupView(this)).show();
    }
}
