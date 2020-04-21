package com.leo.leolibrary.net;

/**
 * Created by Android开发 on 2018/5/10.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.leo.leolibrary.R;


/**
 * 检查网络
 */
public class NetUtil {

    public static boolean isNetConnect(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo!=null){
                return true;
            }else {
                MsgUtil.showToastMsg(context,context.getResources().getString(R.string.no_net));
                return false;
            }
        }
        return false;
    }
}