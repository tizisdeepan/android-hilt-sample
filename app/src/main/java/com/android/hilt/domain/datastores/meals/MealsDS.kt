package com.android.hilt.domain.datastores.meals

import com.android.hilt.data.Meals
import com.android.hilt.data.Result
import kotlinx.coroutines.flow.Flow

interface MealsDS {
    fun getAllMovies(): Flow<Result<List<Meals>>>
}