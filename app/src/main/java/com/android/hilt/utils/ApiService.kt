package com.android.hilt.utils

import com.android.hilt.data.MealsObject
import com.android.hilt.data.RecipeObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/mealplans/generate")
    fun getMeals(@Query("targetCalories") targetCalories: Int): Call<MealsObject>

    @GET("recipes/{recipeId}/information")
    fun getRecipe(@Path("recipeId") recipeId: Int): Call<RecipeObject>
}