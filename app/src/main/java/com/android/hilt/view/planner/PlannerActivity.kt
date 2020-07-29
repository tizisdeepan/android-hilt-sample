package com.android.hilt.view.planner

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.hilt.R
import com.android.hilt.data.MealInfo
import com.android.hilt.data.Meals
import com.android.hilt.data.Result
import com.android.hilt.view.recipe.RecipeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_planner.*

@AndroidEntryPoint
class PlannerActivity : AppCompatActivity() {

    private val plannerViewModel: PlannerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planner)
        title = "Planner"

        plannerRecyclerView.layoutManager = LinearLayoutManager(this)

        plannerViewModel.mealsResult.observe(this, Observer {
            handleResult(it)
        })

        plannerViewModel.getAllMovies()
    }

    private fun handleResult(result: Result<List<Meals>>) {
        when (result) {
            is Result.Loading -> {
                loadingFrame.visibility = View.VISIBLE
                errorFrame.visibility = View.GONE
                dataFrame.visibility = View.GONE
            }
            is Result.Success -> {
                loadingFrame.visibility = View.GONE
                errorFrame.visibility = View.GONE
                dataFrame.visibility = View.VISIBLE
                val mealInfo: ArrayList<MealInfo> = ArrayList()
                result.data.forEach { meal ->
                    mealInfo.add(meal.getMealInfo())
                }
                plannerRecyclerView.adapter = PlannerAdapter(mealInfo) {
                    startRecipeScreen(it)
                }
            }
            is Result.Failure -> {
                loadingFrame.visibility = View.GONE
                errorFrame.visibility = View.VISIBLE
                dataFrame.visibility = View.GONE
                error.text = result.error
            }
        }
    }

    private fun startRecipeScreen(id: Int) {
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }
}