package com.example.lodjinha.home

import com.example.domain.models.Banner
import com.example.domain.models.Product
import com.example.domain.models.Category

object HomeContract {
    interface View {
        fun showBannerList(list: List<Banner>)
        fun showCategoriesList(list: List<Category>)
        fun showBestSellersList(list: List<Product>)
    }

    interface Presenter {
        fun getBannerList()
        fun getCategoriesList()
        fun getBestSellersList()
    }
}