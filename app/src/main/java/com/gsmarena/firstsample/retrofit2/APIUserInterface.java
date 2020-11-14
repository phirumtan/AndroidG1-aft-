package com.gsmarena.firstsample.retrofit2;

import com.gsmarena.firstsample.retrofit2.item.RegisterItem;
import com.gsmarena.firstsample.retrofit2.item.ResponseRegister;
import com.gsmarena.firstsample.retrofit2.item.UserItem;
import com.gsmarena.firstsample.ui.model.ResponseCreateUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIUserInterface {

    @FormUrlEncoded
    @POST("/api/register")
    Call<ResponseRegister> doRegister(@Field("email") String email, @Field("password") String password);

    @POST("/api/login")
    Call<Object> doLogin(@Body RegisterItem user);

    @GET("/api/users?")
    Call<UserItem> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/user")
    Call<ResponseCreateUser> doCreate(@Field("name") String name, @Field("job") String job);
}
