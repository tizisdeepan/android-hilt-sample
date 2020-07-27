package com.android.hilt.view.planner

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.hilt.R
import com.android.hilt.data.entities.responses.Result
import com.android.hilt.view.planner.PlannerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class PlannerActivity : AppCompatActivity() {

    private val plannerViewModel: PlannerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plannerViewModel.getAllMovies()

        plannerViewModel.mealsResult.observe(this, Observer {
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