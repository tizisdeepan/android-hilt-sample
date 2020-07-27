package com.android.hilt.domain.datastores.recipe

import com.android.hilt.data.RecipeObject
import com.android.hilt.data.Result
import kotlinx.coroutines.flow.Flow

interface RecipeDS {
    fun getRecipe(recipeId: Int): Flow<Result<RecipeObject>>
}