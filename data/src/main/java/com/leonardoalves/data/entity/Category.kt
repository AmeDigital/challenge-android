package com.leonardoalves.data.entity

import com.google.gson.annotations.SerializedName

data class Category(
	@SerializedName("id") val id: Int = 0,
	@SerializedName("urlImagem") val urlImagem: String? = null,
	@SerializedName("descricao") val descricao: String? = null
)