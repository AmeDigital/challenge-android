package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_model.api.LodjinhaApi
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.api.SafeApi

open class ProdutoRepositoryImpl(val api: LodjinhaApi) : BaseRepositoryImpl(),
    ProdutoRepository {

    override suspend fun getMaisVendidos(): Resource<List<Produto>> {
        return safeCallForApiDataResponse {
            api.getMaisVendidos()
        }
    }

    override suspend fun getProduto(produtoId: Long): Resource<Produto> {
        return safeCallForApiDirectResponse {
            api.getProduto(produtoId)
        }
    }

    override suspend fun reservarProduto(produtoId: Long): Resource<String> {
        val call = SafeApi.safeCall {
            api.reservarProduto(produtoId)
        }
        return when (call) {
            is Resource.Success -> Resource.Success(call.value.result)
            is Resource.Failure -> call
            else -> Resource.Requesting
        }
    }
}