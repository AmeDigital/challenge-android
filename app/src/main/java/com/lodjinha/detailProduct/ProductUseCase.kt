package com.lodjinha.detailProduct

import androidx.lifecycle.LiveData
import com.lodjinha.model.Products
import com.lodjinha.repository.LodjinhaRepository
import com.lodjinha.repository.utils.Resource

class ProductUseCase(private val repository: LodjinhaRepository) {

    suspend operator fun invoke(page: Int, categoryId: Int): LiveData<Resource<Products>> =
        repository.getProductsAsync(page = page, categoryId = categoryId)
}
