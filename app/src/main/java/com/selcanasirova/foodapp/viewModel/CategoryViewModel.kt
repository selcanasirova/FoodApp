package com.selcanasirova.foodapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selcanasirova.foodapp.data.MealsByCategory
import com.selcanasirova.foodapp.network.Network
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel(){

    val apiUtils = Network.api
    val mealsByCategoryLiveData = MutableLiveData<List<MealsByCategory>>()


    fun getMealsByCategory(category: String){
        viewModelScope.launch {
            val response = apiUtils.getMealByCategory(category)
            if(response.isSuccessful){
                response.body()?.meals.also {
                    mealsByCategoryLiveData.value = it
                }       }
        }
    }

    fun observerMealsByCategoryLiveData(): MutableLiveData<List<MealsByCategory>> {
        return mealsByCategoryLiveData
    }
}