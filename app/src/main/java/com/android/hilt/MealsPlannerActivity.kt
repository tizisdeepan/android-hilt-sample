package com.android.hilt

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.hilt.data.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MealsPlannerActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getAllMovies()

        mainViewModel.mealsResult.observe(this, Observer {
            Log.e("MOVIES", it.toString())
            when (it) {
                is Result.Loading -> {
                    loadingFrame.visibility = View.VISIBLE
                    errorFrame.visibility = View.GONE
                    dataFrame.visibility = View.GONE
                }
                is Result.Success -> {
                    loadingFrame.visibility = View.GONE
                    errorFrame.visibility = View.GONE
                    dataFrame.visibility = View.VISIBLE
                }
                is Result.Failure -> {
                    loadingFrame.visibility = View.GONE
                    errorFrame.visibility = View.VISIBLE
                    dataFrame.visibility = View.GONE
                    error.text = it.error
                }
            }
        })
    }
}