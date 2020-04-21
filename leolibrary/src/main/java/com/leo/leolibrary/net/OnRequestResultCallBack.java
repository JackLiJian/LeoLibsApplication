package com.leo.leolibrary.net;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public interface OnRequestResultCallBack<T> {
    void analysisObjectData(T t);

    void analysisArrayData(List<T> t);

    void dealEmptyData(String message);

}
