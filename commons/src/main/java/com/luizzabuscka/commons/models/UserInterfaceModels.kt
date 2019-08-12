package com.luizzabuscka.commons.models

import java.io.Serializable

data class Banner(
    val urlImage: String,
    val urlLink: String
)

data class Category(
    val id: Number,
    val description: String,
    val urlImage: String
) : Serializable

data class Product(
    val id: Number,
    val name: String,
    val urlImage: String,
    val description: String,
    val priceFrom: String,
    val price: String,
    val category: Category
) : Serializable