package com.leo.leolibsapplication.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;

import com.leo.leolibsapplication.R;
import com.lxj.xpopup.core.AttachPopupView;
import com.lxj.xpopup.impl.AttachListPopupView;

public class CustomAttachPopupView extends AttachPopupView {
        public CustomAttachPopupView(@NonNull Context context) {
            super(context);
        }

        @Override
        protected int getImplLayoutId() {
            return R.layout.adt_xrecyclerview_item;
        }

        @Override
        protected void onCreate() {
            super.onCreate();
            findViewById(R.id.adt_text).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastUtils.showShort("赞");
                }
            });
//            findViewById(R.id.tv_comment).setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    ToastUtils.showShort("评论");
//                }
//            });
        }

}
