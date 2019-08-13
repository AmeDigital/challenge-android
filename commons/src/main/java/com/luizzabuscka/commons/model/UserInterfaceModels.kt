package com.luizzabuscka.commons.model

import java.io.Serializable

data class Banner(
    val urlImage: String,
    val urlLink: String
)

data class Category(
    val id: Int,
    val description: String,
    val urlImage: String
) : Serializable

data class Product(
    val id: Int,
    val name: String,
    val urlImage: String,
    val description: String,
    val priceFrom: String,
    val price: String,
    val category: Category
) : Serializable