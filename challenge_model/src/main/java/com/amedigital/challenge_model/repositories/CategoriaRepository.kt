package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.api.Resource

interface CategoriaRepository {
    suspend fun getCategorias(): Resource<List<Categoria>>
}