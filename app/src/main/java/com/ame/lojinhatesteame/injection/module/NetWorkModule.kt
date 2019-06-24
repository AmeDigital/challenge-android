package com.ame.lojinhatesteame.injection.module

import com.ame.lojinhatesteame.data.remote.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetWorkModule {
    @Provides
    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
                                    .baseUrl("https://alodjinha.herokuapp.com/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
        return retrofit.create(ApiService::class.java)
    }
}