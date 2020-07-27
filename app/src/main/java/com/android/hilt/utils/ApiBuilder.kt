package com.android.hilt.utils

import android.util.Log
import com.android.hilt.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiBuilder {

    private val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)

    fun makeApiCall(): ApiService {
        okHttpClient.addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("x-rapidapi-host", BuildConfig.HOST)
                .addHeader("x-rapidapi-key", BuildConfig.API_KEY)
                .addHeader("useQueryString", "true").build()
            val response = chain.proceed(request)
            Log.e("URL", "${response.code()}:${request.url().encodedPath()}")
            response
        }
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls()
                .setLenient().create())).client(okHttpClient.build()).baseUrl(BuildConfig.BASE_URL)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}