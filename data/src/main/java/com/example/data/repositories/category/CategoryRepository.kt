package com.example.data.repositories.category

import com.example.data.models.mapper.CategoryMapper
import com.example.data.remote.Api
import com.example.domain.models.Category
import com.example.domain.repositories.category.ICategoryListRepository
import io.reactivex.Observable

class CategoryRepository(val api: Api) : ICategoryListRepository {
    override fun getCategoryList(): Observable<List<Category>> {
        return api.getCategoryList().map{
            CategoryMapper.mapFrom(it.results)
        }
    }
}