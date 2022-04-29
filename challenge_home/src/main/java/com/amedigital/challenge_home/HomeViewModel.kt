package com.amedigital.challenge_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amedigital.challenge_model.Banner
import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.repositories.BannerRepository
import com.amedigital.challenge_model.repositories.CategoriaRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bannerRepository: BannerRepository,
    private val categoriaRepository: CategoriaRepository
) : ViewModel() {
    private val _banners: MutableLiveData<Resource<List<Banner>>> = MutableLiveData()
    val banners: LiveData<Resource<List<Banner>>>
        get() = _banners

    private val _categorias: MutableLiveData<Resource<List<Categoria>>> = MutableLiveData()
    val categorias: LiveData<Resource<List<Categoria>>>
        get() = _categorias

    init {
        loadBanners()
        loadCategorias()
    }

    fun loadBanners() = viewModelScope.launch {
        _banners.value = Resource.Requesting
        _banners.value = bannerRepository.getBanners()
    }

    fun loadCategorias() = viewModelScope.launch {
        _categorias.value = Resource.Requesting
        _categorias.value = categoriaRepository.getCategorias()
    }
}