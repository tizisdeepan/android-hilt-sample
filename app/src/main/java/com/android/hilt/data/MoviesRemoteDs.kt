package com.android.hilt.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRemoteDs @Inject constructor() : MoviesDS {

    override fun getAllMovies() = flow {
        val result: Result<List<Movies>> = Result.Failure("Remote Failed")
        delay(5000)
        emit(result)
    }
}