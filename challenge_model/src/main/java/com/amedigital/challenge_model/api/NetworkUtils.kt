package com.amedigital.challenge_model.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object NetworkUtils {
    fun createApiService(): LodjinhaApi {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://alodjinha.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientLog())
            .build()

        return retrofit.create(LodjinhaApi::class.java)
    }

    private fun okHttpClientLog(): OkHttpClient {
        val client = Builder()
        client.addInterceptor(createInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
        return client.build()
    }

    private fun createInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}