package com.example.domain.repositories.category

import com.example.domain.models.Category
import io.reactivex.Observable

interface ICategoryListRepository {
    fun getCategoryList(): Observable<List<Category>>
}