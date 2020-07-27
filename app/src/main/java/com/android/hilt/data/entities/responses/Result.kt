package com.android.hilt.data.entities.responses

sealed class Result<out DATA> {
    data class Success<DATA>(val data: DATA) : Result<DATA>()
    data class Failure<DATA>(val error: String) : Result<DATA>()
    object Loading : Result<Nothing>()
}