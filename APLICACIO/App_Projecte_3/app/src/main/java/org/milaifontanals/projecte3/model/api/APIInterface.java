package org.milaifontanals.projecte3.model.api;

import org.milaifontanals.projecte3.model.apiRuta.RespostaRuta;
import org.milaifontanals.projecte3.model.apiUbicacions.RespostaCrearUbicacio;
import org.milaifontanals.projecte3.model.apiUbicacions.RespostaGetUbicaciones;
import org.milaifontanals.projecte3.model.logOut.RespostaLogOut;
import org.milaifontanals.projecte3.model.optimitzarRequest.OptimitzarRequest;
import org.milaifontanals.projecte3.model.userLogin.RespostaLogin;
import org.milaifontanals.projecte3.model.userRegister.RespostaRegister;
import org.milaifontanals.projecte3.ui.crearUbicacio.CrearUbicacio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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
    @Headers("Accept: application/json")
    @POST("register")
    Call<RespostaRegister> registerUser(
            @Field("name") String name,
            @Field("apellidos") String apellidos,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String passwordConfirm);

    @GET("getListaUbicaciones")
    @Headers("Accept: application/json")
    Call<RespostaGetUbicaciones> getLlistaUbicacions(@Header("Authorization") String token);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("optimizar")
    Call<RespostaRuta> getOptimitzador(
            @Header("Authorization") String token,
            @Body OptimitzarRequest or
            );

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("ubicacion/crear")
    Call<RespostaCrearUbicacio> crearUbicacio(
            @Header("Authorization") String token,
            @Field("nombre") String nombre,
            @Field("direccion") String direccion,
            @Field("coordenada") String coord,
            @Field("observaciones") String observacion,
            @Field("fav") int favorito
    );

    @POST("logout")
    @Headers("Accept: application/json")
    Call<RespostaLogOut> logout(
            @Header("Authorization") String token
    );
}
