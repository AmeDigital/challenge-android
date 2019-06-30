package com.example.lodjinha.modelVO

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BannerVO(
    val id: Int,
    val destinationUrl: String?,
    val imageUrl: String?
) : Parcelable