package com.example.examenemg.helpers

import com.example.examenemg.interfaces.APIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    var instance: APIService;
    private const val TOKEN_FB = "4797916173588240";
    private const val BASEURL = "https://superheroapi.com/api/$TOKEN_FB/";

    init {
        val buildRetrofit = Retrofit
            .Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        instance = buildRetrofit.create(APIService::class.java);
    }
}