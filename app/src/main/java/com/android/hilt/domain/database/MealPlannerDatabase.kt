package com.android.hilt.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.hilt.data.Meals

@Database(entities = [Meals::class], version = 1)
abstract class MealPlannerDatabase : RoomDatabase() {
    abstract fun mealsDao(): MealsDao
}