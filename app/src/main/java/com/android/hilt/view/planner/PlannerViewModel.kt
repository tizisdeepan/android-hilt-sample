package com.android.hilt.view.planner

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.android.hilt.domain.usecases.MealsUseCase
import com.android.hilt.data.Result
import com.android.hilt.data.Meals
import kotlinx.coroutines.flow.onStart

class PlannerViewModel @ViewModelInject constructor(private val mainUseCase: MealsUseCase) :
    ViewModel() {

    var mealsResult: LiveData<Result<List<Meals>>> = MutableLiveData()

    fun getAllMovies() {
        mealsResult = mainUseCase.getAllMovies().onStart { emit(Result.Loading) }
            .asLiveData(viewModelScope.coroutineContext)
    }
}