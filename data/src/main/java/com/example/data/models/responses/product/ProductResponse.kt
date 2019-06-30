package com.example.data.models.responses.product

import com.example.domain.models.Category
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("categoria") val category: Category,
    @SerializedName("descricao") val description : String,
    @SerializedName("nome") val name : String,
    @SerializedName("precoDe") val oldPrice : Float,
    @SerializedName("precoPor") val newPrice : Float,
    @SerializedName("urlImagem") val imageUrl : String
)