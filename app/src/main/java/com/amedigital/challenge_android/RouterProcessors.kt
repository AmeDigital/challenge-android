package com.amedigital.challenge_android

import com.amedigital.challenge_produto.ProdutoRouterProcessor
import com.amedigital.coreui.RouterProcessor

fun routerProcessors() = listOf<RouterProcessor>(
    ProdutoRouterProcessor()
)