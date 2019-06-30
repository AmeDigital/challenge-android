package com.example.data.models.mapper

import com.example.data.models.responses.product.ProductResponse
import com.example.domain.models.Product

object ProductMapper {
    fun map(response: ProductResponse) =
            Product(
                id = response.id,
                category = response.category,
                description = response.description,
                name = response.name,
                oldPrice = response.oldPrice,
                newPrice = response.newPrice,
                imageUrl = response.imageUrl
            )
}