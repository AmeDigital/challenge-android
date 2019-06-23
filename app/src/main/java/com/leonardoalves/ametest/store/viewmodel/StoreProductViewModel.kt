package com.leonardoalves.ametest.store.viewmodel

import com.leonardoalves.ametest.custom.ViewModel

class StoreProductViewModel(
    val id: Int,
    val name: String,
    val originalPrice: Float?,
    val price: Float,
    val picture: String?,
    val description: String?,
    val categoryName: String
) : ViewModel