package com.example.domain.usecases.category

import com.example.domain.models.Category
import com.example.domain.repositories.category.ICategoryListRepository
import io.reactivex.Observable

class CategoryListUseCase(val repository: ICategoryListRepository) : CategoryList {
    override fun execute(): Observable<List<Category>> {
        return repository.getCategoryList()
    }
}