package com.br.cinesky.api

import br.com.amedigital.lojinha.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiBuilder {
    companion object {
        private val apiInterface: ApiInterface? = null
        fun getWebService(): ApiInterface {

            if (apiInterface != null) {
                return apiInterface
            }

            val retrofit = Retrofit.Builder().baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}