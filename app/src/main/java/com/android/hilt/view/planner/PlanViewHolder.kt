package com.android.hilt.view.planner

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.hilt.data.MealInfo
import kotlinx.android.synthetic.main.item_plan.view.*

class PlanViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val planItemFrame = view.planItemFrame
    private val title = view.title

    fun setData(mealInfo: MealInfo) {
        title.text = mealInfo.title
    }
}