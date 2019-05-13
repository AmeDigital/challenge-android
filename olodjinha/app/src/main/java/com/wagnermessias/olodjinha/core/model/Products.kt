package com.wagnermessias.olodjinha.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Products (
    @SerializedName("data")
    val list: ArrayList<Product>
):Serializable