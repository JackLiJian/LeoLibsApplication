package com.leo.leolibrary.net;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Android开发 on 2018/5/10.
 */

public class MsgUtil {

    private static String Tag = "MSG";
    private static String Boundry = "---------->";
    private static Toast toast;


    public static void showToastMsg(Context context, String msg) {
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showLogMsg(String msg) {
        Log.e(Tag, Boundry + msg);
    }

    public static void showLogMsg(int msg) {
        Log.e(Tag, Boundry + msg);
    }
}
