package com.example.data.models.mapper

import com.example.data.models.responses.product.ProductResponse

object ProductListMapper {
    fun mapFrom(response: List<ProductResponse>) =
        response.map {
            ProductMapper.map(it)
        }
}