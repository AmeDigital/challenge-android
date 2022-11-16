package com.amedigital.challenge_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amedigital.challenge_model.Banner
import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.repositories.BannerRepository
import com.amedigital.challenge_model.repositories.CategoriaRepository
import com.amedigital.challenge_model.repositories.ProdutoRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bannerRepository: BannerRepository,
    private val categoriaRepository: CategoriaRepository,
    private val produtoRepository: ProdutoRepository
) : ViewModel() {
    private val _banners: MutableLiveData<Resource<List<Banner>>> = MutableLiveData()
    val banners: LiveData<Resource<List<Banner>>>
        get() = _banners

    private val _categorias: MutableLiveData<Resource<List<Categoria>>> = MutableLiveData()
    val categorias: LiveData<Resource<List<Categoria>>>
        get() = _categorias

    private val _maisVendidos: MutableLiveData<Resource<List<Produto>>> = MutableLiveData()
    val maisVendidos: LiveData<Resource<List<Produto>>>
        get() = _maisVendidos

    init {
        loadBanners()
        loadCategorias()
        loadMaisVendidos()
    }

    fun loadBanners() = viewModelScope.launch {
        _banners.value = Resource.Requesting
        _banners.value = bannerRepository.getBanners()
    }

    fun loadCategorias() = viewModelScope.launch {
        _categorias.value = Resource.Requesting
        _categorias.value = categoriaRepository.getCategorias()
    }

    fun loadMaisVendidos() = viewModelScope.launch {
        _maisVendidos.value = Resource.Requesting
        _maisVendidos.value = produtoRepository.getMaisVendidos()
    }
}