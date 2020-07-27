package com.android.hilt.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@Entity
data class Meals(@PrimaryKey(autoGenerate = true) var id: Int = 0,
    var day: Int,
    var mealPlanId: Int,
    var slot: Int,
    var position: Int,
    var type: String,
    var value: String) {
    fun getMealInfo(): MealInfo = Gson().fromJson(value, MealInfo::class.java)
}