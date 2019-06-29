package com.lodjinha.home

import androidx.lifecycle.LiveData
import com.lodjinha.model.Banners
import com.lodjinha.model.Categories
import com.lodjinha.model.Products
import com.lodjinha.repository.LodjinhaRepository
import com.lodjinha.repository.utils.Resource

class TopSellingUseCase(private val repository: LodjinhaRepository) {

    suspend operator fun invoke(): LiveData<Resource<Products>> = repository.getTopSellingProductsAsync()
}
