package com.android.hilt.utils

import com.android.hilt.data.MealsObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/mealplans/generate")
    fun getMeals(@Query("targetCalories") targetCalories: Int): Call<MealsObject>
}