package com.android.hilt.data.entities

data class Meals(var day: Int,
    var mealPlanId: Int,
    var slot: Int,
    var position: Int,
    var type: String,
    var value: String)