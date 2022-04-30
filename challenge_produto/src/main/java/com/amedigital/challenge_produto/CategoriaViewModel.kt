package com.amedigital.challenge_produto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.repositories.ProdutoRepository
import kotlinx.coroutines.launch

class CategoriaViewModel(
    private val categoriaId: Long,
    private val produtoRepository: ProdutoRepository
) : ViewModel() {
    private val _produtos: MutableLiveData<Resource<List<Produto>>> = MutableLiveData()
    val produtos: LiveData<Resource<List<Produto>>>
        get() = _produtos

    init {
        loadProdutos()
    }

    fun loadProdutos() = viewModelScope.launch {
        _produtos.value = Resource.Requesting
        _produtos.value = produtoRepository.getByCategoria(categoriaId)
    }
}