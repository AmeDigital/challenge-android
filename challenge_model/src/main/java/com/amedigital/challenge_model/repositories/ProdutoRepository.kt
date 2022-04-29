package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_model.api.Resource

interface ProdutoRepository {
    suspend fun getMaisVendidos(): Resource<List<Produto>>
    suspend fun getProduto(produtoId: Int): Resource<Produto>
    suspend fun reservarProduto(produto: Produto): Resource<String>
}