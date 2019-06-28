package com.lodjinha.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lodjinha.R
import com.lodjinha.base.BaseViewModel
import com.lodjinha.model.Banners
import com.lodjinha.model.Categories
import com.lodjinha.repository.AppDispatchers
import com.lodjinha.repository.utils.Resource
import com.lodjinha.utils.Event
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val bannerUseCase: BannerUseCase,
    private val categoriesUseCase: CategoriesUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val bannersMediator = MediatorLiveData<Resource<Banners>>()
    val banners: LiveData<Resource<Banners>> get() = bannersMediator
    private var bannersSource: LiveData<Resource<Banners>> = MutableLiveData()

    private val categoriesMediator = MediatorLiveData<Resource<Categories>>()
    val categories: LiveData<Resource<Categories>> get() = categoriesMediator
    private var categoriesSource: LiveData<Resource<Categories>> = MutableLiveData()

    init {
        getBanners()
        getCategories()
    }

    private fun getBanners() = viewModelScope.launch(dispatchers.main) {
        bannersMediator.removeSource(bannersSource) // We make sure there is only one source of liveData (allowing us properly refresh)

        withContext(dispatchers.io) {
            bannersSource = bannerUseCase()
        }

        bannersMediator.addSource(bannersSource) {
            bannersMediator.value = it
            if (it.status == Resource.Status.ERROR) {
                snackBarErrorMediator.value = Event(R.string.an_error_happened)
            }
        }
    }

    private fun getCategories() = viewModelScope.launch(dispatchers.main) {
        categoriesMediator.removeSource(categoriesSource)

        withContext(dispatchers.io) {
            categoriesSource = categoriesUseCase()
        }

        categoriesMediator.addSource(categoriesSource) {
            categoriesMediator.value = it
            if (it.status == Resource.Status.ERROR) {
                snackBarErrorMediator.value = Event(R.string.an_error_happened)
            }
        }
    }
}
