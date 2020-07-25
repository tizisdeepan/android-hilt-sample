package com.android.hilt.data

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesUseCase @Inject constructor(var local: MoviesLocalDS, var remote: MoviesRemoteDs) {

    fun getAllMovies(): Flow<Result<List<Movies>>> = flow {
        emit(Result.Loading)
        coroutineScope {
            local.getAllMovies().collect {
                if (it is Result.Success && it.data.isNotEmpty()) emit(it)
            }
            remote.getAllMovies().collect {
                emit(it)
            }
        }
    }
}