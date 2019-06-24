package com.ame.lojinhatesteame.data.remote.response

import com.ame.lojinhatesteame.data.model.Category
import com.google.gson.annotations.SerializedName

class BodyResponseCategory (@SerializedName("data") var data: ArrayList<Category>)