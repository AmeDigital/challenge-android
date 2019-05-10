package com.wagnermessias.olodjinha.feature.home

import com.wagnermessias.olodjinha.core.model.Banners
import com.wagnermessias.olodjinha.core.model.Categories
import com.wagnermessias.olodjinha.core.model.Products

sealed class HomeViewState {
    data class BannersList(val banners: Banners): HomeViewState()
    data class CategoriesList(val categories: Categories): HomeViewState()
    data class ProductsList(val products: Products): HomeViewState()
    data class ShowErro(val value: Boolean ): HomeViewState()
}
