package com.android.hilt.di

import com.android.hilt.utils.ApiBuilder
import com.android.hilt.utils.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getApiService(): ApiService = ApiBuilder().makeApiCall()
}