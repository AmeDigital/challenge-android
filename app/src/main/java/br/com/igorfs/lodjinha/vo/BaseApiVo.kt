package br.com.igorfs.lodjinha.vo

import com.google.gson.annotations.SerializedName

class BaseApiVo<T> (

    @SerializedName("data")
    val getList: List<T>
)