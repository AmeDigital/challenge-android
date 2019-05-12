package com.wagnermessias.olodjinha.core.data

import com.wagnermessias.olodjinha.core.data.remote.ALodjinhaApi
import com.wagnermessias.olodjinha.core.model.Categories
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CategoryRepository(
    private val aLodjinhaApi: ALodjinhaApi
) {
    suspend fun getCategories(): Deferred<Response<Categories>> = withContext(Dispatchers.IO) {
        aLodjinhaApi.getCategories()
    }
}