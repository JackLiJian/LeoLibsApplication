package com.leo.leolibrary.net;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/2.
 */

public abstract class BaseSubscriber<T> implements Observer<ResponseBody> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
