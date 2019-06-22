package com.leonardoalves.repository

import com.google.gson.GsonBuilder
import com.leonardoalves.repository.api.Api
import com.leonardoalves.repository.repository.StoreRepository
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val SERVER_URL = "https://alodjinha.herokuapp.com"
const val TIMEOUT = 120L

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder().baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

val remoteDataSourceModule = module {
    single { createOkHttpClient() }
    single { createWebService<Api>(get(), SERVER_URL) }
}

val repositoryModule = module {
    single { StoreRepository(get()) }
}
