package com.android.hilt

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    var currentValue: MutableLiveData<Int> = mainRepository.currentValue

    fun getCurrentValue() {
        mainRepository.getCurrentValue()
    }
}