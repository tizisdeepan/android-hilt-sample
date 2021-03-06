package com.android.hilt.view.planner

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.android.hilt.domain.usecases.MealsUseCase
import com.android.hilt.data.Result
import com.android.hilt.data.Meals
import kotlinx.coroutines.flow.onStart

class PlannerViewModel @ViewModelInject constructor(private val useCase: MealsUseCase) :
    ViewModel() {

    var mealsResult: MediatorLiveData<Result<List<Meals>>> = MediatorLiveData()

    fun getAllMovies() {
        mealsResult.addSource(useCase.getAllMovies().onStart { emit(Result.Loading) }
            .asLiveData(viewModelScope.coroutineContext)) {
            mealsResult.value = it
        }
    }
}