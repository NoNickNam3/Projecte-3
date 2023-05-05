package org.milaifontanals.projecte3.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIAdapter {

    private static APIInterface API_SERVICE;

    private static Retrofit retrofit = null;

    public static APIInterface getApiService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        if(API_SERVICE == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://169.254.40.141/Projecte-3/PathFinder/public/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            API_SERVICE = retrofit.create(APIInterface.class);
        }

        return API_SERVICE;
    }

}
