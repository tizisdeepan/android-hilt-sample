package com.android.hilt.data

import kotlinx.coroutines.flow.Flow

interface MoviesDS {
    fun getAllMovies(): Flow<Result<List<Movies>>>
}