package com.amedigital.challenge_produto

import android.content.Context
import android.net.Uri
import com.amedigital.coreui.DeepLinkProcessor
import com.amedigital.coreui.DeepLinkProcessorChain
import com.amedigital.coreui.RouterManager
import com.amedigital.coreui.startDeepLink

class ProdutoDeepLinkProcessor : DeepLinkProcessor {
    override fun process(
        router: RouterManager,
        context: Context,
        deeplink: Uri,
        chain: DeepLinkProcessorChain?
    ) {
        if (!isProcessed(context, deeplink)) {
            chain?.next(router, context, deeplink)
        }
    }

    private fun isProcessed(context: Context, deeplink: Uri): Boolean {
        when (deeplink.host) {
            "produto",
            "categoria"
            -> {
                startDeepLink(context, deeplink)
                return true
            }
            else -> return false
        }
    }
}