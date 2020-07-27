package com.android.hilt.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meals(@PrimaryKey(autoGenerate = true) var id: Int = 0,
    var day: Int,
    var mealPlanId: Int,
    var slot: Int,
    var position: Int,
    var type: String,
    var value: String)