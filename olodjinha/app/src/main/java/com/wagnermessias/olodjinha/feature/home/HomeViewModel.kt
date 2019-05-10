package com.wagnermessias.olodjinha.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wagnermessias.olodjinha.core.base.BaseViewModel
import com.wagnermessias.olodjinha.core.interactor.BannerInteractor
import com.wagnermessias.olodjinha.core.interactor.CategoryInteractor
import com.wagnermessias.olodjinha.core.interactor.ProductInteractor
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bannerInteractor: BannerInteractor,
    private val categoryInteractor: CategoryInteractor,
    private val productInteractor: ProductInteractor

) : BaseViewModel() {
    private val state = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState> = state

    fun loadBanners() {
        launch {
            try {
                val responseBanners = bannerInteractor.getBanners()

                if (responseBanners.isSuccessful) {
                    state.value = responseBanners.body()?.let {
                        HomeViewState.BannersList(it)
                    }
                } else {
                    reportError()
                }

            } catch (e: Exception) {
                reportError()
            }
        }
    }

    fun loadCategories() {
        launch {
            try {
                val responseCategories = categoryInteractor.getCategories()

                if (responseCategories.isSuccessful) {
                    state.value = responseCategories.body()?.let {
                        HomeViewState.CategoriesList(it)
                    }
                } else {
                    reportError()
                }

            } catch (e: Exception) {
                reportError()
            }
        }
    }

    fun loadBestSellers() {
        launch {
            try {
                val responseProducts = productInteractor.getBestSellers()

                if (responseProducts.isSuccessful) {
                    state.value = responseProducts.body()?.let {
                        HomeViewState.ProductsList(it)
                    }
                } else {
                    reportError()
                }

            } catch (e: Exception) {
                reportError()
            }
        }
    }

    private fun reportError() {
        state.value = HomeViewState.ShowErro(true)
    }
}