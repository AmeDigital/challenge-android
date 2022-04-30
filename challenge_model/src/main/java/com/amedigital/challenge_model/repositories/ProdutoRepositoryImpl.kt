package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_model.api.ApiDataResponse
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

    override suspend fun getProdutos(): Resource<List<Produto>> {
        return safeCallForApiDataResponse {
            api.getProdutos()
        }
    }

    override suspend fun getByCategoria(categoriaId: Int): Resource<List<Produto>> {
        return safeCallForApiDataResponse {
            //TODO: retornar direto da api
            val itemsFiltered =
                api.getProdutos().data.takeWhile { produto -> produto.categoria.id == categoriaId }
            ApiDataResponse(
                itemsFiltered, 0, itemsFiltered.size
            )
        }
    }

    override suspend fun getProduto(produtoId: Int): Resource<Produto> {
        return safeCallForApiDirectResponse {
            api.getProduto(produtoId)
        }
    }

    override suspend fun reservarProduto(produtoId: Int): Resource<String> {
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