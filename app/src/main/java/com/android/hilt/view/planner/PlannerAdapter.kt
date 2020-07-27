package com.android.hilt.view.planner

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hilt.R
import com.android.hilt.data.MealInfo

class PlannerAdapter(var mealInfo: List<MealInfo>, var onClick: (Int) -> Unit) :
    RecyclerView.Adapter<PlanViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        context = parent.context
        return PlanViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_plan, parent, false))
    }

    override fun getItemCount(): Int = mealInfo.size

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val info = mealInfo[holder.adapterPosition]
        holder.setData(info)
        holder.planItemFrame.setOnClickListener {
            onClick(info.id)
        }
    }
}