package com.android.hilt.domain.datastores.meals

import com.android.hilt.data.Result
import com.android.hilt.data.Meals
import com.android.hilt.utils.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MealsRemoteDs @Inject constructor(var api: ApiService) :
    MealsDS {

    override fun getAllMovies() = flow {
        var result: Result<List<Meals>> = Result.Failure(
            "Something went wrong")

        api.getMeals(2000).let {
            val response = it.execute()
            val mealsObject = response.body()
            if (response.isSuccessful && mealsObject != null && mealsObject.items.isNotEmpty()) {
                result = Result.Success(mealsObject.items)
            }
            emit(result)
        }
    }.flowOn(Dispatchers.IO)
}