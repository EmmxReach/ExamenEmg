package com.example.examenemg.interfaces

import com.example.examenemg.model.SuperheroModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getSuperHeroById(@Url url: String): Response<SuperheroModel>
}