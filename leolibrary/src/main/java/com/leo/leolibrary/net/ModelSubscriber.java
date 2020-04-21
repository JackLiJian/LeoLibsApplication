package com.leo.leolibrary.net;

import android.content.Context;
import android.text.TextUtils;


import com.leo.leolibrary.R;
import com.leo.leolibrary.utils.MsgUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;


/**
 * Created by Administrator on 2017/7/5.
 * This class is to deal the data from service.
 */

public class ModelSubscriber<T> extends BaseSubscriber<ResponseBody> {

    private OnRequestResultCallBack<T> onRequestResultCallBack;

    private Type finalNeedType;

    private String jsonType;

    private Context mContext;

    /**
     * @param mContext                上下文
     * @param onRequestResultCallBack 网络数据请求的回调
     * @param jsonType                定义返回的jsonType：JsonType.JSON_OBJECT：返回的数据是对象
     *                                JsonType.JSON_ARRAY:  返回的数据是List
     */
    public ModelSubscriber(Context mContext, OnRequestResultCallBack<T> onRequestResultCallBack, String jsonType) {
        this.mContext = mContext;
        this.onRequestResultCallBack = onRequestResultCallBack;
        this.jsonType = jsonType;
    }

    @Override
    public void onError(Throwable e) {
        if (!NetUtil.isNetConnect(mContext)) {
            MsgUtil.showToastMsg(mContext, mContext.getResources().getString(R.string.no_net));
        }

    }

    @Override
    public void onSubscribe(Disposable d) {
        Type[] types = ReflectionUtil.getParameterizedTypeswithInterfaces(onRequestResultCallBack);
        if (ReflectionUtil.methodHandler(types) == null || ReflectionUtil.methodHandler(types).size() == 0) {
            throw new NullPointerException("callBack<T> 中T不合法");
        }
        finalNeedType = ReflectionUtil.methodHandler(types).get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onNext(ResponseBody responseBody) {
        try {
            String jsonStr = responseBody.string();
            MsgUtil.showLogMsg("json=" + jsonStr);
//            int state;
//            try {
//                JSONObject jsonObject = new JSONObject(jsonStr);
//                state = jsonObject.getInt("state");
//                String message = jsonObject.getString("message");
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


            //数据类别的判断处理，使用GSON将JSON数据转换成对应的T模型，并使用onRequestResultCallBack进行回调处理
            if (jsonType.equals(JsonType.JSON_OBJECT)) {
                try {
                    HttpResult<T> httpResult = GsonTransform.fromObjectJson(jsonStr, ReflectionUtil.newInstance(finalNeedType).getClass());
                    T t = httpResult.getData();
                    String message = httpResult.getMsg();
                    boolean success = httpResult.getSuccess();
                    if (success) {
                        onRequestResultCallBack.analysisObjectData(t);
                    } else {
                        if (!TextUtils.isEmpty(httpResult.getCode())) {
                            if (httpResult.getCode().equals("5201") || httpResult.getCode().equals("5301") || httpResult.getCode().equals("5401")) {
                                onRequestResultCallBack.dealEmptyData(httpResult.getCode());
                            } else {
                                onRequestResultCallBack.dealEmptyData(message);
                            }
                        } else {
                            onRequestResultCallBack.dealEmptyData(message);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (jsonType.equals(JsonType.JSON_ARRAY)) {
                try {
                    HttpListResult<T> httpListResult = GsonTransform.fromArrayJson(jsonStr, ReflectionUtil.newInstance(finalNeedType).getClass());
                    List<T> t = httpListResult.getData();

                    String message = httpListResult.getMsg();
                    if (httpListResult.isSuccess()) {
                        onRequestResultCallBack.analysisArrayData(t);
                    } else {
                        onRequestResultCallBack.dealEmptyData(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
