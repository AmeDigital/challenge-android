package com.luizzabuscka.data.webservice

import com.google.gson.annotations.SerializedName

data class BannerWS(
    @SerializedName("id") val id: Int,
    @SerializedName("urlImagem") val urlImage: String,
    @SerializedName("linkUrl") val urlLink: String
)

data class BannersResponse(
    @SerializedName("data") val banners: List<BannerWS>
)

data class CategoryWS(
    @SerializedName("id") val id: Int,
    @SerializedName("descricao") val description: String,
    @SerializedName("urlImagem") val urlImage: String
)

data class CategoriesResponse(
    @SerializedName("data") val categories: List<CategoryWS>
)

data class ProductWS(
    @SerializedName("id") val id: Int,
    @SerializedName("nome") val name: String,
    @SerializedName("urlImagem") val urlImage: String,
    @SerializedName("descricao") val description: String,
    @SerializedName("precoDe") val priceFrom: Number,
    @SerializedName("precoPor") val price: Number,
    @SerializedName("categoria") val category: CategoryWS
)

data class ProductsResponse(
    @SerializedName("data") val products: List<ProductWS>,
    @SerializedName("offset") val offset: Number,
    @SerializedName("total") val total: Number
)

