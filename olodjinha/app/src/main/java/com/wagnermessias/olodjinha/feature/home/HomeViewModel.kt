package com.wagnermessias.olodjinha.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wagnermessias.olodjinha.core.base.BaseViewModel
import com.wagnermessias.olodjinha.core.interactor.LoadBannerInteractor
import com.wagnermessias.olodjinha.core.interactor.LoadBestSellersInteractor
import com.wagnermessias.olodjinha.core.interactor.LoadCategoryInteractor
import kotlinx.coroutines.launch

class HomeViewModel(
    private val loadBannerInteractor: LoadBannerInteractor,
    private val loadCategoryInteractor: LoadCategoryInteractor,
    private val loadBestSellersInteractor: LoadBestSellersInteractor

) : BaseViewModel() {
    private val state = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState> = state

    fun loadBanners() {
        launch {
            try {
                val responseBanners = loadBannerInteractor.execute()

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
                val responseCategories = loadCategoryInteractor.execute()

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
                val responseProducts = loadBestSellersInteractor.execute()

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