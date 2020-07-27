package com.android.hilt.domain.usecases

import com.android.hilt.data.Result
import com.android.hilt.domain.datastores.meals.MealsLocalDS
import com.android.hilt.domain.datastores.meals.MealsRemoteDs
import com.android.hilt.data.Meals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MealsUseCase @Inject constructor(var local: MealsLocalDS, var remote: MealsRemoteDs) {

    fun getAllMovies(): Flow<Result<List<Meals>>> = flow {
        var localResult: Result<List<Meals>> = Result.Failure()
        local.getAllMovies().collect {
            if (it is Result.Success && it.data.isNotEmpty()) localResult = it
        }
        remote.getAllMovies().collect {
            if (it is Result.Success && it.data.isNotEmpty()) local.insertMeals(it.data)
            if (it is Result.Failure) emit(localResult)
            else emit(it)
        }
    }.flowOn(Dispatchers.IO)
}