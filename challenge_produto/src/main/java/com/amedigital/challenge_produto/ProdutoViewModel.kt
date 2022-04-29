package com.amedigital.challenge_produto

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

class ProdutoViewModel(
    private val produtoId: Long,
    private val produtoRepository: ProdutoRepository
) : ViewModel() {
    private val _produto: MutableLiveData<Resource<Produto>> = MutableLiveData()
    val produto: LiveData<Resource<Produto>>
        get() = _produto

    init {
        loadProduto()
    }

    fun loadProduto() = viewModelScope.launch {
        _produto.value = Resource.Requesting
        _produto.value = produtoRepository.getProduto(produtoId)
    }

    private val _reservado: MutableLiveData<Resource<String>> = MutableLiveData()
    val reservado: LiveData<Resource<String>>
        get() = _reservado

    fun reservarProduto() = viewModelScope.launch {
        _reservado.value = Resource.Requesting
        _reservado.value = produtoRepository.reservarProduto(produtoId)
    }
}