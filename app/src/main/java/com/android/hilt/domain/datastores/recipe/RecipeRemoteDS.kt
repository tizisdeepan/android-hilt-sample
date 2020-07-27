package com.android.hilt.domain.datastores.recipe

import com.android.hilt.data.RecipeObject
import com.android.hilt.data.Result
import com.android.hilt.utils.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class RecipeRemoteDS @Inject constructor(var api: ApiService) : RecipeDS {

    override fun getRecipe(recipeId: Int): Flow<Result<RecipeObject>> = flow {
        var result: Result<RecipeObject> = Result.Failure("Something went wrong")
        api.getRecipe(recipeId).let {
            val response = try {
                it.execute()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            val body = response?.body()
            if (response != null && response.isSuccessful && body != null) {
                result = Result.Success(body)
            }
            emit(result)
        }
    }.flowOn(Dispatchers.IO)

}