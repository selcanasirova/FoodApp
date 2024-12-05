package com.selcanasirova.foodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selcanasirova.foodapp.data.Category
import com.selcanasirova.foodapp.data.CategoryList
import com.selcanasirova.foodapp.data.MealsByCategory
import com.selcanasirova.foodapp.data.Meal
import com.selcanasirova.foodapp.data.MealList
import com.selcanasirova.foodapp.network.Network
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private val apiUtils = Network.api
    private var randomMeadLiveData = MutableLiveData<Meal>()
    private var popularMealLiveData = MutableLiveData<List<MealsByCategory>>()
    private var categoriesMealLiveData = MutableLiveData<List<Category>>()

    fun getRandomMeal(){
        apiUtils.getRandomAMeal().enqueue(object: Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.isSuccessful){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMeadLiveData.value = randomMeal
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment",t.message.toString())
            }

        })
    }
    fun observeRandomMealLiveData() : LiveData<Meal>{
        return randomMeadLiveData
    }

    fun getPopularMealItems(category: String = "SeaFood"){
        viewModelScope.launch {
            val response = apiUtils.getPopularItems(category)
            if (response.isSuccessful){
                response.body()?.meals.also { popularMealLiveData.value = it }
            }
        }
    }
    fun observePopularMealLiveData(): LiveData<List<MealsByCategory>> {
        return popularMealLiveData
    }

    fun getCategories(){
        viewModelScope.launch {
            val response = apiUtils.getCategoryMeals()
            if (response.isSuccessful){
                response.body()?.also {categoryList->
                    categoriesMealLiveData.postValue(categoryList.categories)}
            }
        }
    }

    fun observerCategoriesmealLiveData(): LiveData<List<Category>> {
        return categoriesMealLiveData
    }



}