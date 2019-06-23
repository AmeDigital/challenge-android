package com.leonardoalves.data.entity

import com.google.gson.annotations.SerializedName

data class Product(
	@SerializedName("id") val id: Int = 0,
	@SerializedName("nome") val nome: String? = null,
	@SerializedName("precoDe") val precoDe: Float? = null,
	@SerializedName("precoPor") val precoPor: Float? = null,
	@SerializedName("urlImagem") val urlImagem: String? = null,
	@SerializedName("descricao") val descricao: String? = null,
	@SerializedName("categoria") val categoria: Category? = null
)