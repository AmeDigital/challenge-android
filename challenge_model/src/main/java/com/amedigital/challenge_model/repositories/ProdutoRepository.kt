package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_model.api.Resource

interface ProdutoRepository {
    suspend fun getMaisVendidos(): Resource<List<Produto>>
    suspend fun getProduto(produtoId: Long): Resource<Produto>
    suspend fun reservarProduto(produtoId: Long): Resource<String>
}