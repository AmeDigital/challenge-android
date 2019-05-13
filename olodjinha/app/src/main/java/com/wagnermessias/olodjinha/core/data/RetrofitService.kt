package com.wagnermessias.olodjinha.core.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitService {
    companion object {
        private const val BASE_URL = "https://alodjinha.herokuapp.com"

        fun retrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build()
        }

        private val client: OkHttpClient = OkHttpClient.Builder().apply {
        }.build()
    }
}