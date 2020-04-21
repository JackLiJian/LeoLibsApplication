package com.leo.leolibrary.net;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/27.
 */

public class GsonTransform<T> {

    private static final Gson mGson = new Gson();


    public static HttpResult fromObjectJson(String json, Class clazz) {
        Type objectType = type(HttpResult.class, clazz);
        return mGson.fromJson(json, objectType);
    }

    public static HttpListResult fromArrayJson(String json, Class clazz) {
        Type objectType = type(HttpListResult.class, clazz);
        return mGson.fromJson(json, objectType);
    }


    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz)

    {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }

    private static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

}
