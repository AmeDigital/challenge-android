package com.wagnermessias.olodjinha.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wagnermessias.olodjinha.core.base.BaseViewModel
import com.wagnermessias.olodjinha.core.interactor.LoadBannerInteractor
import com.wagnermessias.olodjinha.core.interactor.LoadBestSellersInteractor
import com.wagnermessias.olodjinha.core.interactor.LoadCategoryInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(
    private val loadBannerInteractor: LoadBannerInteractor,
    private val loadCategoryInteractor: LoadCategoryInteractor,
    private val loadBestSellersInteractor: LoadBestSellersInteractor,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseViewModel(mainDispatcher) {
    private val state = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState> = state

    fun loadBanners() {
        scope.launch {
            try {
                val responseBanners = loadBannerInteractor.execute()

                if (responseBanners.isSuccessful) {
                    state.value = responseBanners.body()?.let {
                        HomeViewState.BannersList(it)
                    }
                } else {
                    state.value = HomeViewState.ServerErrorBanners
                }
            } catch (e: IOException) {
                state.value = HomeViewState.NetworkError
            }
        }
    }

    fun loadCategories() {
        launch {
            try {
                val responseCategories = loadCategoryInteractor.execute()

                if (responseCategories.isSuccessful) {
                    responseCategories.body()?.let {
                        state.value = HomeViewState.CategoriesList(it)
                    }
                } else {
                    state.value = HomeViewState.ServerErrorCategories
                }

            } catch (e: IOException) {
                state.value = HomeViewState.NetworkError
            }
        }
    }

    fun loadBestSellers() {
        launch {
            try {
                val responseProducts = loadBestSellersInteractor.execute()

                if (responseProducts.isSuccessful) {

                    responseProducts.body()?.let {
                        state.value = HomeViewState.ProductsList(it)
                    }
                } else {
                    state.value = HomeViewState.ServerErrorBestSellers
                }

            } catch (e: IOException) {
                state.value = HomeViewState.NetworkError
            }
        }
    }

}