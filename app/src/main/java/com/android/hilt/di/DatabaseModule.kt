package com.android.hilt.di

import android.app.Application
import androidx.room.Room
import com.android.hilt.domain.database.MealPlannerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(application: Application): MealPlannerDatabase =
        Room.databaseBuilder(application, MealPlannerDatabase::class.java, "meal_planner_db")
            .build()
}