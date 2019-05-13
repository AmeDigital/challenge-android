package com.wagnermessias.olodjinha.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Category(
    @SerializedName("id")
    val id: Int,
    @SerializedName("urlImagem")
    val urlImage: String,
    @SerializedName("descricao")
    val description
    : String
): Serializable
