package br.com.igorfs.lodjinha.viewmodel

import androidx.lifecycle.ViewModel
import br.com.igorfs.lodjinha.repository.BannerRepository
import br.com.igorfs.lodjinha.repository.CategoryRepository

class HomeViewModel: ViewModel() {

    private val bannerRepository = BannerRepository()
    private val categoryRepository = CategoryRepository()

    fun getBannerList() = bannerRepository.getBannerList()
    fun fetchBannerList() = bannerRepository.fetchBannerList()

    fun getCategories() = categoryRepository.getCategories()
    fun fetchCategoryList() = categoryRepository.fetchCategoryList()
}