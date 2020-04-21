package com.leo.leolibrary.net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Android开发 on 2018/5/14.
 */

public interface NetApiService {

    @POST()
    Observable<ResponseBody> post(
            @Url() String url,
            @Body() RequestBody requestBody);

    @PUT()
    Observable<ResponseBody> put(
            @Url() String url,
            @Body() RequestBody requestBody);

    @GET()
    Observable<ResponseBody> get(
            @Url() String url);

    @Multipart
    @POST()
    Observable<ResponseBody> upLoadImage(
            @Url() String url,
            @Part("image\"; filename=\"image.jpg") RequestBody requestBody);


    @POST()
    Observable<ResponseBody> postUrl(
            @Url() String url,
            @QueryMap() Map<String, Object> map);
}
