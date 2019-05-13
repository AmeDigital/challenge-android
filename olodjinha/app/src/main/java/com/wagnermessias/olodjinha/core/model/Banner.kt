package com.wagnermessias.olodjinha.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Banner(
    @SerializedName("id")
    val id: Int,
    @SerializedName("urlImagem")
    val urlImagem: String,
    @SerializedName("linkUrl")
    val linkUrl: String
): Serializable
