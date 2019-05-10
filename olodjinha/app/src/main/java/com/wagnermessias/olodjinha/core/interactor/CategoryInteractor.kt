package com.wagnermessias.olodjinha.core.interactor

import com.wagnermessias.olodjinha.core.data.CategoryRepository
import com.wagnermessias.olodjinha.core.model.Categories
import retrofit2.Response

class CategoryInteractor(
    private val repository: CategoryRepository
){
    suspend fun getCategories(): Response<Categories> {
        val call = repository.getCategories()
        return call.await()
    }
}