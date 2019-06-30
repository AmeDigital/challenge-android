package com.example.data.models.responses.category

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("descricao") val description : String,
    @SerializedName("urlImagem") val imageUrl : String
)