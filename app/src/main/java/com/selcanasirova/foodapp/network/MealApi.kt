package com.selcanasirova.foodapp.network

import com.selcanasirova.foodapp.data.CategoryList
import com.selcanasirova.foodapp.data.MealsByCategoryList
import com.selcanasirova.foodapp.data.MealList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomAMeal(): Call<MealList>

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") id: String): Response<MealList>

    @GET("filter.php?")
//    fun getPopularItems(@Query("c") categoryName:String): Call<CategoryList>
    suspend fun getPopularItems(@Query("c") categoryName: String): Response<MealsByCategoryList>

    @GET("categories.php")
    suspend fun getCategoryMeals():Response<CategoryList>

    @GET("filter.php")
    suspend fun getMealByCategory(@Query("c") category: String): Response<MealsByCategoryList>
}