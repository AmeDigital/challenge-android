package com.amedigital.challenge_produto

import android.content.Context
import android.net.Uri
import com.amedigital.coreui.RouterProcessor
import com.amedigital.coreui.RouterProcessorChain
import com.amedigital.coreui.RouterManager
import com.amedigital.coreui.start

class ProdutoRouterProcessor : RouterProcessor {
    override fun process(
        router: RouterManager,
        context: Context,
        uri: Uri,
        chain: RouterProcessorChain?
    ) {
        if (!isProcessed(context, uri)) {
            chain?.next(router, context, uri)
        }
    }

    private fun isProcessed(context: Context, uri: Uri): Boolean {
        when (uri.host) {
            ProdutoActivity.HOST,
            CategoriaActivity.HOST
            -> {
                start(context, uri)
                return true
            }
            else -> return false
        }
    }
}