package com.lodjinha.home

import androidx.lifecycle.LiveData
import com.lodjinha.model.Banners
import com.lodjinha.model.Categories
import com.lodjinha.repository.LodjinhaRepository
import com.lodjinha.repository.utils.Resource

class CategoriesUseCase(private val repository: LodjinhaRepository) {

    suspend operator fun invoke(): LiveData<Resource<Categories>> = repository.getCategoriesAsync()
}
