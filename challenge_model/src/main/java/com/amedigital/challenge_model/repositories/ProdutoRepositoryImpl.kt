package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_model.api.ApiResponse
import com.amedigital.challenge_model.api.LodjinhaApi
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.api.SafeApi

open class ProdutoRepositoryImpl(val api: LodjinhaApi) : BaseRepositoryImpl(),
    ProdutoRepository {

    override suspend fun getMaisVendidos(): Resource<List<Produto>> {
        return safeCallForApiResponse {
            api.getMaisVendidos()
        }
    }

    override suspend fun getProduto(produtoId: Int): Resource<Produto> {
        return safeCallForApiResponse {
            api.getProduto(produtoId)
        }
    }

    override suspend fun reservarProduto(produto: Produto): Resource<String> {
        val call = SafeApi.safeCall {
            api.reservarProduto(produto.id, produto)
        }
        return when (call) {
            is Resource.Success -> Resource.Success(call.value.result)
            is Resource.Failure -> call
            else -> Resource.Requesting
        }
    }
}