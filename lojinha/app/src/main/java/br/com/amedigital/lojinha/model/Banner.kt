package br.com.amedigital.lojinha.model

import java.io.Serializable

data class Banner(var id: Int,
                  var urlImagem: String,
                  var linkUrl: String): Serializable
