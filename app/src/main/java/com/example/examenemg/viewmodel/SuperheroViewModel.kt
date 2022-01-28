package com.example.examenemg.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenemg.model.SuperheroModel
import com.example.examenemg.network.SuperheroService
import kotlinx.coroutines.launch

class SuperheroViewModel: ViewModel() {

    val superheros = MutableLiveData<List<SuperheroModel>>()
    private val resultHeroes = mutableListOf<SuperheroModel>()

    val isBusy = MutableLiveData<Boolean>()
    private val service = SuperheroService()

    fun onCreate(x: Int, pageSize: Int){
        resultHeroes.clear()
        viewModelScope.launch {
            isBusy.postValue(true)
            for (s in x..pageSize){
                val superhero = service.getSuperheroById(s)
                if (superhero != null){
                    resultHeroes.add(superhero)
                }
            }
            superheros.postValue(resultHeroes)
            isBusy.postValue(false)
        }
    }
}