package com.wagnermessias.olodjinha.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Banners (
    @SerializedName("data")
    val list: List<Banner>
):Serializable