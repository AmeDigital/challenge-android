package br.com.amedigital.lojinha.model

import java.io.Serializable

class Produto(
    var id: Int? = null,
    val nome: String? = null,
    val urlImagem: String? = null,
    val descricao: String? = null,
    val precoDe: Double? = null,
    val precoPor: Double? = null,
    val categoria: Categoria? = null
) : Serializable