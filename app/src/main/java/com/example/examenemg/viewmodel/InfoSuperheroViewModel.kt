package com.example.examenemg.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.examenemg.model.SuperheroModel

class InfoSuperheroViewModel: ViewModel() {

    val superhero = MutableLiveData<SuperheroModel>();

    fun onCreate(data: SuperheroModel){
        superhero.postValue(data)
    }
}