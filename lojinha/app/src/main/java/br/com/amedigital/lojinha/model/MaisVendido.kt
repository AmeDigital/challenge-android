package br.com.amedigital.lojinha.model

import java.io.Serializable

class MaisVendido(
    val id: Int? = null,
    val nome: String? = null,
    val urlImagem: String? = null,
    val descricao: String? = null,
    val precoDe: Double? = null,
    val precoPor: Double? = null,
    val categorias: List<Categoria>? = null
): Serializable