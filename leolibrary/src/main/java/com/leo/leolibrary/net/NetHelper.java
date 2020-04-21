package com.leo.leolibrary.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;


/**
 * 此类是对retrofit的第二次封装，以便统一管理
 */
public class NetHelper {

    /**
     * BASE_URL:网络请求的BaseUrl
     **/
//    public static final String BASE_URL = "http://192.168.1.116:8080/jyh/";

//    正式服务器
    public static final String BASE_URL = "http://service.dijiudao.com/jyh/";

    @SuppressLint("StaticFieldLeak")
    private static HttpFactory httpFactory;

    private static final int DEFAULT_TIME_OUT = 20;

    private NetHelper() {
    }

    private static volatile NetHelper instance;

    public static NetHelper getInstance() {
        if (instance == null) {
            synchronized (NetHelper.class) {
                if (instance == null) {
                    instance = new NetHelper();
                }
            }
        }
        return instance;
    }

    /**
     * retrofit的get方法封装
     */

    public void get(Context context, String url, BaseSubscriber<ResponseBody> subscriber) {

        Map<String, Object> headers = new HashMap<>();
        //签处理
//        String tokenInfo = SpUtil.getSharedPreferences(context).getString(SpUtil.TOKEN, "");
        String tokenInfo = "";
        if (!TextUtils.isEmpty(tokenInfo)) {
            headers.put("token", tokenInfo);
        }
        httpFactory = new HttpFactory.Builder(context)
                .addHeader(headers)
                .baseUrl(BASE_URL)
                .addCache(false)
                .connectTimeout(DEFAULT_TIME_OUT)
                .build();

        httpFactory.get(url, subscriber);

    }


    /**
     * retrofit的post方法封装 数据通过请求体
     *
     * @param context    请求的context
     * @param url        网络请求的url
     * @param param      post请求的参数
     * @param subscriber 网络请求的事件
     */

    public void postData(Context context, String url, Map<String, Object> param, BaseSubscriber<ResponseBody> subscriber) {

        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        //签处理
//        String tokenInfo = SpUtil.getSharedPreferences(context).getString(SpUtil.TOKEN, "");
        String tokenInfo = "";
        if (!TextUtils.isEmpty(tokenInfo)) {
            headers.put("token", tokenInfo);
        }


        httpFactory = new HttpFactory.Builder(context)
                .addHeader(headers)
                .baseUrl(BASE_URL)
                .addCache(false)
                .connectTimeout(DEFAULT_TIME_OUT)
                .build();

        httpFactory.post(url, param, subscriber);

    }

    /**
     * retrofit的post方法封装 数据通过请求体
     *
     * @param context    请求的context
     * @param url        网络请求的url
     * @param param      post请求的参数
     * @param subscriber 网络请求的事件
     */

    public void putData(Context context, String url, Map<String, Object> param, BaseSubscriber<ResponseBody> subscriber) {

        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        //签处理
//        String tokenInfo = SpUtil.getSharedPreferences(context).getString(SpUtil.TOKEN, "");
        String tokenInfo = "";
        if (!TextUtils.isEmpty(tokenInfo)) {
            headers.put("token", tokenInfo);
        }


        httpFactory = new HttpFactory.Builder(context)
                .addHeader(headers)
                .baseUrl(BASE_URL)
                .addCache(false)
                .connectTimeout(DEFAULT_TIME_OUT)
                .build();

        httpFactory.put(url, param, subscriber);

    }

    /**
     * 图片文件上传
     */

    public void uploadImageSingle(Context context, String url, File file, BaseSubscriber<ResponseBody> subscriber) {

        Map<String, Object> headers = new HashMap<>();
        headers.put("User-Agent", HeaderUtils.getUserAgent(context));

        httpFactory = new HttpFactory.Builder(context)
                .addHeader(headers)
                .baseUrl(BASE_URL)
                .addCache(true)
                .connectTimeout(DEFAULT_TIME_OUT)
                .build();

        httpFactory.uploadImage(url, file, subscriber);

    }


}
