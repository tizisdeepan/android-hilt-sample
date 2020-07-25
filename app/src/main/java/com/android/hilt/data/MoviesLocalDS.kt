package com.android.hilt.data

import android.app.Application
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesLocalDS @Inject constructor(private val context: Application) : MoviesDS {

    override fun getAllMovies() = flow {
        val result: Result<List<Movies>> = Result.Failure("Local Failed")
        delay(1000)
        emit(result)
    }
}