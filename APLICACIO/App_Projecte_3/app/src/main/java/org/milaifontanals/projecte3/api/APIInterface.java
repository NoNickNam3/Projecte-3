package org.milaifontanals.projecte3.api;

import org.milaifontanals.projecte3.model.userLogin.RespostaLogin;
import org.milaifontanals.projecte3.model.userRegister.RespostaRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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
    Call<RespostaLogin> loginUser(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<RespostaRegister> registerUser(
            @Field("name") String name,
            @Field("apellidos") String apellidos,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String passwordConfirm);
}
