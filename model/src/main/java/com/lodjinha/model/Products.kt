package com.lodjinha.model

data class Products(
    val data: List<Product>,
    val offset: Int,
    val total: Int
)
