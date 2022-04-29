package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.api.LodjinhaApi
import com.amedigital.challenge_model.api.Resource

open class CategoriaRepositoryImpl(val api: LodjinhaApi) : BaseRepositoryImpl(), CategoriaRepository {

    override suspend fun getCategorias(): Resource<List<Categoria>> {
        return safeCallForApiDataResponse {
            api.getCategorias()
        }
    }
}