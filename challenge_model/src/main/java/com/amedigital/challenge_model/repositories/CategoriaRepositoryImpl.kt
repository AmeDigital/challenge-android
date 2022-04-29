package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.api.LodjinhaApi
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.api.SafeApi

open class CategoriaRepositoryImpl(val api: LodjinhaApi) : CategoriaRepository {

    override suspend fun getCategorias(): Resource<List<Categoria>> {
        val call = SafeApi.safeCall {
            api.getCategorias()
        }
        return when (call) {
            is Resource.Success -> Resource.Success(call.value.data)
            is Resource.Failure -> call
            else -> Resource.Requesting
        }
    }

}