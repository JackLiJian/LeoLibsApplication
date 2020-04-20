package com.leo.leolibrary.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * 软键盘弹出 在某个控件之上
 * Created by Leo on 2018/12/11.
 */

public class SoftUtil {

    private static SoftUtil softUtil;

    public SoftUtil() {
    }

    public static SoftUtil getInstance() {
        if (softUtil == null) {
            softUtil = new SoftUtil();
        }
        return softUtil;
    }

    /**
     *
     * @param context
     * @param container_layout 父布局容器
     * @param view 需要展示的控件
     * @param extra_height
     */
    public void setSoftListener(final Activity context, final ViewGroup container_layout, final View view, final int extra_height) {
        container_layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //getWindowVisibleDisplayFrame() 获取当前窗口可视区域大小
                context.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int screenHeight = context.getWindow().getDecorView().getHeight();
                //键盘弹出时，可视区域大小改变，屏幕高度-窗口可视区域高度=键盘弹出高度
                int softHeight = screenHeight - rect.bottom;

                /**
                 * 上移的距离 = 键盘的高度 - 按钮距离屏幕底部的高度(如果手机高度很大，上移的距离会是负数，界面将不会上移)
                 * 按钮距离屏幕底部的高度是用屏幕高度 - 按钮底部距离父布局顶部的高度
                 * 注意这里 btn.getBottom() 是按钮底部距离父布局顶部的高度，这里也就是距离最外层布局顶部高度
                 */
                int scrollDistance = softHeight - (screenHeight - view.getBottom());
                if (scrollDistance > 0) {
                    //具体移动距离可自行调整
                    container_layout.scrollTo(0, scrollDistance + extra_height);
                } else {
                    container_layout.scrollTo(0, 0);
                }
            }
        });
    }


}
