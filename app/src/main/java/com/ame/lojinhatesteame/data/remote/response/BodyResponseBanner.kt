package com.ame.lojinhatesteame.data.remote.response

import com.ame.lojinhatesteame.data.model.Banner
import com.google.gson.annotations.SerializedName

class BodyResponseBanner (
    @SerializedName("data") var data: ArrayList<Banner>
)