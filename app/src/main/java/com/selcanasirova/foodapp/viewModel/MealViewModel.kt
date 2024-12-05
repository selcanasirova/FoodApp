package com.selcanasirova.foodapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selcanasirova.foodapp.data.Meal
import com.selcanasirova.foodapp.data.MealList
import com.selcanasirova.foodapp.network.Network
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel : ViewModel() {
    private val apiUtils = Network.api
    var mealDetailLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id: String) {
       viewModelScope.launch {
           val response = apiUtils.getMealDetails(id)
           if (response.isSuccessful){
               println("success: ${response.body()}")
               mealDetailLiveData.value = response.body()?.meals?.get(0)
           }
       }
    }

    fun observeMealDetailsLiveData(): LiveData<Meal> {
        return mealDetailLiveData
    }
}