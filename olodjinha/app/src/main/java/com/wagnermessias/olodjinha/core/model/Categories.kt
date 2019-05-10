package com.wagnermessias.olodjinha.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Categories (
    @SerializedName("data")
    val list: List<Category>
):Serializable