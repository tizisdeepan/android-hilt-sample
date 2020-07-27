package com.android.hilt.domain.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.android.hilt.data.entities.Meals

@Dao
abstract class MealsDao {
    @Transaction
    open suspend fun insertMeals(meals: List<Meals>) {
        nukeTable()
        insertAllMeals(meals)
    }

    @Insert
    abstract fun insertAllMeals(meals: List<Meals>)

    @Query("SELECT * FROM Meals")
    abstract fun getAllMeals(): List<Meals>

    @Query("DELETE FROM Meals")
    abstract fun nukeTable()
}