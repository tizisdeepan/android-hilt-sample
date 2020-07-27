package com.android.hilt.data

import com.android.hilt.data.entities.Meals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MealsRemoteDs @Inject constructor() : MealsDS {

    override fun getAllMovies() = flow {
        var result: Result<List<Meals>> = Result.Failure("Something went wrong")

        ApiBuilder().makeApiCall().getMeals(2000).let {
            val response = it.execute()
            val mealsObject = response.body()
            if (response.isSuccessful && mealsObject != null && mealsObject.items.isNotEmpty()) {
                result = Result.Success(mealsObject.items)
            }
            emit(result)
        }
    }.flowOn(Dispatchers.IO)
}