package com.example.data.models.mapper

import com.example.data.models.responses.category.CategoryResponse
import com.example.domain.models.Category

object CategoryMapper {
    fun mapFrom(response: List<CategoryResponse>) =
        response.map {
            Category(
                id = it.id,
                imageUrl = it.imageUrl,
                description = it.description
            )
        }
}