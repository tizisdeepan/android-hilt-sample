package com.android.hilt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getAllMovies()

        mainViewModel.moviesResult.observe(this, Observer {
            Log.e("MOVIES", it.toString())
        })

        next.setOnClickListener {
            startActivity(Intent(this, DetailsActivity::class.java))
        }
    }
}