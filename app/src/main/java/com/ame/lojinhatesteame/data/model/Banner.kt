package com.ame.lojinhatesteame.data.model

import com.google.gson.annotations.SerializedName

class Banner {
    @SerializedName("id") var id: Int = 0
    @SerializedName("urlImagem") var urlImage: String = ""
    @SerializedName("linkUrl") var linkUrl: String = ""
}