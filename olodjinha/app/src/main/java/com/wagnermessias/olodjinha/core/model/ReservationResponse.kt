package com.wagnermessias.olodjinha.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ReservationResponse(
    @SerializedName("result")
    val result: String,
    @SerializedName("mensagem")
    val mensagem: String
): Serializable
