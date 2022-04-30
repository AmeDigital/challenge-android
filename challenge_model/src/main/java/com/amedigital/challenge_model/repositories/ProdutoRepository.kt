package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_model.api.Resource

interface ProdutoRepository {
    suspend fun getProdutos(): Resource<List<Produto>>
    suspend fun getMaisVendidos(): Resource<List<Produto>>
    suspend fun getByCategoria(categoriaId: Int): Resource<List<Produto>>
    suspend fun getProduto(produtoId: Int): Resource<Produto>
    suspend fun reservarProduto(produtoId: Int): Resource<String>
}