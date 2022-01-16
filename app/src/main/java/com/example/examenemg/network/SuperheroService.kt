package com.example.examenemg.network

import com.example.examenemg.helpers.RetrofitHelper
import com.example.examenemg.model.SuperheroModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SuperheroService {

    suspend fun getSuperheroById(id: Int): SuperheroModel? {
         return withContext(Dispatchers.IO){
             val call = RetrofitHelper.instance.getSuperHeroById("$id");
             if (call.isSuccessful){
                 call.body()  //Retornar la response
             }
             else
                null
         }
    }
}