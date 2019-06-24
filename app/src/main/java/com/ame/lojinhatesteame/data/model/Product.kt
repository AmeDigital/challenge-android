package com.ame.lojinhatesteame.data.model

import android.text.Html
import com.ame.lojinhatesteame.util.formatToMoney
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product (
    @SerializedName("id") var id: Int = 0,
    @SerializedName("urlImagem") var urlImage: String = "",
    @SerializedName("nome") var name: String = "",
    @SerializedName("descricao") var description: String = "",
    @SerializedName("precoDe") var priceOf: Double = 0.0,
    @SerializedName("precoPor") var priceFor: Double = 0.0,
    @SerializedName("categoria") var category:Category? = null) : Serializable {

    val priceForFormated: String get() = "de ${priceFor.formatToMoney()}"
    val priceOfFormated: String get() = "por ${priceOf.formatToMoney()}"
    val descriptionHtmlFormat: String get() = Html.fromHtml(description).toString()
}