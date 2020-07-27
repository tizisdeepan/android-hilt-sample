package com.android.hilt.data

import android.app.Application
import com.android.hilt.data.entities.Meals
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealsLocalDS @Inject constructor(private val context: Application) : MealsDS {

    override fun getAllMovies() = flow {
        val result: Result<List<Meals>> = Result.Failure("Local Failed")
        emit(result)
    }
}