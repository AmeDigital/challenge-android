package com.example.data.models.responses.banner

import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("linkUrl") val destinationUrl : String,
    @SerializedName("urlImagem") val imageUrl : String
)