package com.android.hilt

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import kotlin.random.Random

class MainRepository @Inject constructor() {

    val currentValue: MutableLiveData<Int> = MutableLiveData()

    fun getCurrentValue() {
        Handler().postDelayed({
            currentValue.value = Random.nextInt(0, 100)
        }, 2000)
    }
}