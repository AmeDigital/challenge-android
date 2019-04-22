package br.com.amedigital.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RestClient {

    companion object {
        var url_api: String = "https://alodjinha.herokuapp.com/"
    }

    private val httpClient = OkHttpClient.Builder()
    private val builder = Retrofit.Builder()
        .baseUrl(getUrl())
        .addConverterFactory(GsonConverterFactory.create())

    private fun getUrl(): String {
        return url_api
    }

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }
}