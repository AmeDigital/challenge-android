package marco.challenge_android.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Banner(
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("urlImagem") val urlImage: String? = null,
    @field:SerializedName("linkUrl") val linkUrl: String? = null
)

data class BannerResponse(
    @field:SerializedName("data") val bannerList: List<Banner>? = null
)

data class Category(
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("descricao") val description: String? = null,
    @field:SerializedName("urlImagem") val urlImage: String? = null
): Serializable

data class CategoryResponse(
    @field:SerializedName("data") val categoryList: List<Category>? = null
)

data class Product(
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("nome") val name: String? = null,
    @field:SerializedName("urlImagem") val urlImage: String? = null,
    @field:SerializedName("descricao") val description: String? = null,
    @field:SerializedName("precoDe") val priceFrom: Double? = null,
    @field:SerializedName("precoPor") val priceTo: Double? = null,
    @field:SerializedName("categoria") val category: Category? = null
): Serializable

data class ProductResponse(
    @field:SerializedName("data") val productList: List<Product>? = null
)