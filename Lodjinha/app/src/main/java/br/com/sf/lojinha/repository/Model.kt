package br.com.sf.lojinha.repository

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("id")
    val id: Int,
    @SerializedName("linkUrl")
    val url: String
)

data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nome")
    val name: String,

    @SerializedName("descricao")
    val description: String,

    @SerializedName("precoDe")
    val priceFrom: Float,
    @SerializedName("precoPor")
    val priceBy: Float,
    @SerializedName("urlImagem")
    val image: String,
    @SerializedName("categoria")
    val category: Category
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readString(),
        TODO("category")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeFloat(priceFrom)
        parcel.writeFloat(priceBy)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}

data class Category(
    @SerializedName("id")
    val id: Int,

    @SerializedName("descricao")
    val description: String,

    @SerializedName("urlImagem")
    val imageUrl: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}

data class Reserve(
    val result: String,
    val message: String?
)

data class Data<T>(
    @SerializedName("data")
    val content: List<T>,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int
)