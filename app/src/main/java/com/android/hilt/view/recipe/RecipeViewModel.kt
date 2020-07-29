package com.android.hilt.view.recipe

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.android.hilt.data.RecipeObject
import com.android.hilt.data.Result
import com.android.hilt.domain.usecases.RecipeUseCase
import kotlinx.coroutines.flow.onStart

class RecipeViewModel @ViewModelInject constructor(private val useCase: RecipeUseCase) :
    ViewModel() {

    var recipeResult: MediatorLiveData<Result<RecipeObject>> = MediatorLiveData()

    fun getRecipe(recipeId: Int) {
        recipeResult.addSource(useCase.getRecipe(recipeId).onStart { emit(Result.Loading) }
            .asLiveData(viewModelScope.coroutineContext)) {
            recipeResult.value = it
        }
    }
}