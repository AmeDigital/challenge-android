package com.example.domain.models

data class Product(
    val id: Int,
    val category: Category,
    val description: String,
    val name: String,
    val oldPrice: Float,
    val newPrice: Float,
    val imageUrl: String?
)