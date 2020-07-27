package com.android.hilt.domain.usecases

import com.android.hilt.data.RecipeObject
import com.android.hilt.data.Result
import com.android.hilt.domain.datastores.recipe.RecipeRemoteDS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecipeUseCase @Inject constructor(var remote: RecipeRemoteDS) {

    fun getRecipe(recipeId: Int): Flow<Result<RecipeObject>> = flow {
        remote.getRecipe(recipeId).collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO)
}