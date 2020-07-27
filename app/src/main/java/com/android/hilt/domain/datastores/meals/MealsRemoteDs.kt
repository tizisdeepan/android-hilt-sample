package com.android.hilt.domain.datastores.meals

import com.android.hilt.data.Result
import com.android.hilt.data.Meals
import com.android.hilt.utils.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class MealsRemoteDs @Inject constructor(var api: ApiService) : MealsDS {

    override fun getAllMovies() = flow {
        var result: Result<List<Meals>> = Result.Failure("Something went wrong")

        api.getMeals(2000).let {
            val response = try {
                it.execute()
            } catch (e: Exception) {
                null
            }
            val body = response?.body()
            if (response != null && response.isSuccessful && body != null && body.items.isNotEmpty()) {
                result = Result.Success(body.items)
            }
            emit(result)
        }
    }.flowOn(Dispatchers.IO)
}