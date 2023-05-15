package org.milaifontanals.projecte3.api.apiRequests;

import org.milaifontanals.projecte3.model.userLogin.RespostaLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {
/*
    @GET("/api/unknown")
    Call<MultipleResource> doGetListResources();
/*
    @POST("/login")
    Call<Ubicacion> createUser(@Body User user);
/*
    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);
*/
    @FormUrlEncoded
    @POST("login")
    Call<RespostaLogin> loginUser(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("relogin")
    Call<RespostaLogin> reLoginUser(@Field("key") String key);

}
