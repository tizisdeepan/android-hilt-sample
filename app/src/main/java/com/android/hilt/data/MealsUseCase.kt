package com.android.hilt.data

import com.android.hilt.data.entities.Meals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealsUseCase @Inject constructor(var local: MealsLocalDS, var remote: MealsRemoteDs) {

    fun getAllMovies(): Flow<Result<List<Meals>>> = flow {
        local.getAllMovies().collect {
            if (it is Result.Success && it.data.isNotEmpty()) emit(it)
        }
        remote.getAllMovies().collect {
            emit(it)
        }
    }
}