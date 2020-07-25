package com.android.hilt

import android.content.Intent
import android.os.Bundle
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

        mainViewModel.getCurrentValue()

        mainViewModel.currentValue.observe(this, Observer {
            currentValue.text = it.toString()
        })

        next.setOnClickListener {
            startActivity(Intent(this, DetailsActivity::class.java))
        }
    }
}