package com.leonardoalves.data.entity

import com.google.gson.annotations.SerializedName

class Banner {

    @SerializedName("linkUrl") var linkUrl: String? = null
    @SerializedName("id") var id: Int = 0
    @SerializedName("urlImagem") var urlImagem: String? = null

    override fun toString(): String {
        return "Banner{" +
                "linkUrl = '" + linkUrl + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",urlImagem = '" + urlImagem + '\''.toString() +
                "}"
    }
}