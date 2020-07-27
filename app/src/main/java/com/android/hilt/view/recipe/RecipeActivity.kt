package com.android.hilt.view.recipe

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.hilt.R
import com.android.hilt.data.RecipeObject
import com.android.hilt.data.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_recipe.*

@AndroidEntryPoint
class RecipeActivity : AppCompatActivity() {

    private val recipeViewModel: RecipeViewModel by viewModels()

    private val recipeId: Int? by lazy {
        intent.extras?.getInt("ID", -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Recipe"

        recipeId?.let {
            recipeViewModel.getRecipe(it)
        }

        recipeViewModel.recipeResult.observe(this, Observer {
            handleResult(it)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun handleResult(result: Result<RecipeObject>) {
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    instructions.text = Html.fromHtml(result.data.instructions,
                        Html.FROM_HTML_MODE_COMPACT)
                } else {
                    instructions.text = Html.fromHtml(result.data.instructions)
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
}