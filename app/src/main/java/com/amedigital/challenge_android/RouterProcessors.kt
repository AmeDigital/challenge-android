package com.amedigital.challenge_android

import com.amedigital.challenge_produto.ProdutoDeepLinkProcessor
import com.amedigital.coreui.DeepLinkProcessor

fun routerProcessors() = listOf<DeepLinkProcessor>(
    ProdutoDeepLinkProcessor()
)