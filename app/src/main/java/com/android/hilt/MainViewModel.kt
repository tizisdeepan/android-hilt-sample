package com.android.hilt

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.android.hilt.data.MealsUseCase
import com.android.hilt.data.Result
import com.android.hilt.data.entities.Meals
import kotlinx.coroutines.flow.onStart

class MainViewModel @ViewModelInject constructor(private val mainUseCase: MealsUseCase) :
    ViewModel() {

    var mealsResult: LiveData<Result<List<Meals>>> = MutableLiveData()

    fun getAllMovies() {
        mealsResult = mainUseCase.getAllMovies().onStart { emit(Result.Loading) }
            .asLiveData(viewModelScope.coroutineContext)
    }
}