package com.android.hilt

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.android.hilt.data.Movies
import com.android.hilt.data.MoviesUseCase
import com.android.hilt.data.Result

class MainViewModel @ViewModelInject constructor(private val mainUseCase: MoviesUseCase) :
    ViewModel() {

    lateinit var moviesResult: LiveData<Result<List<Movies>>>

    fun getAllMovies() {
        moviesResult = mainUseCase.getAllMovies().asLiveData(viewModelScope.coroutineContext)
    }
}