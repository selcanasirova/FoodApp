package com.selcanasirova.foodapp.data


import com.google.gson.annotations.SerializedName

data class MealsByCategoryList(
    @SerializedName("meals")
    val meals: List<MealsByCategory>
)