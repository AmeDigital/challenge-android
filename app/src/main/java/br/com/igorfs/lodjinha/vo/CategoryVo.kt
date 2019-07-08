package br.com.igorfs.lodjinha.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoryVo(
    val id: Long,
    val descricao: String,
    val urlImagem: String
) : Parcelable