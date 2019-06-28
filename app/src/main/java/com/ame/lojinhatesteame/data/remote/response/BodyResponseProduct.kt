package com.ame.lojinhatesteame.data.remote.response


import com.ame.lojinhatesteame.data.model.Product
import com.google.gson.annotations.SerializedName

data class BodyResponseProduct (@SerializedName("data") var data: ArrayList<Product>)

