package com.android.hilt.data

import com.android.hilt.data.entities.responses.MealsObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/mealplans/generate")
    fun getMeals(@Query("targetCalories") targetCalories: Int): Call<MealsObject>
}