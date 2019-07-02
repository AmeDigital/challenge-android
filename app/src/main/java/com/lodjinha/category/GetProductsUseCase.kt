package com.lodjinha.category

import androidx.lifecycle.LiveData
import com.lodjinha.model.Products
import com.lodjinha.repository.LodjinhaRepository
import com.lodjinha.repository.utils.Resource

class GetProductsUseCase(private val repository: LodjinhaRepository) {

    suspend operator fun invoke(page: Int, categoryId: Int): LiveData<Resource<Products>> =
        repository.getProductsAsync(page = page, categoryId = categoryId)
}
