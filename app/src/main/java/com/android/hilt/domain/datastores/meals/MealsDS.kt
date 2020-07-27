package com.android.hilt.domain.datastores.meals

import com.android.hilt.data.entities.responses.Result
import com.android.hilt.data.entities.Meals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface MealsDS {
    @ExperimentalCoroutinesApi
    fun getAllMovies(): Flow<Result<List<Meals>>>
}