package com.android.hilt.domain.datastores.meals

import com.android.hilt.data.Result
import com.android.hilt.domain.database.MealPlannerDatabase
import com.android.hilt.data.Meals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MealsLocalDS @Inject constructor(private val db: MealPlannerDatabase) : MealsDS {

    override fun getAllMovies() = flow {
        val meals = db.mealsDao().getAllMeals()
        if (meals.isNotEmpty()) {
            val result: Result<List<Meals>> = Result.Success(meals)
            emit(result)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun insertMeals(meals: List<Meals>) {
        db.mealsDao().insertMeals(meals)
    }
}