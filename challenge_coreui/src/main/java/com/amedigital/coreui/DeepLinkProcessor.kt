package com.amedigital.coreui

import android.content.Context
import android.content.Intent
import android.net.Uri

interface DeepLinkProcessor {
    fun process(
        router: RouterManager,
        context: Context,
        deeplink: Uri,
        chain: DeepLinkProcessorChain?
    )
}

fun DeepLinkProcessor.startDeepLink(context: Context, deeplink: Uri) {
    val intent = Intent(deeplink.host, deeplink)
    context.startActivity(intent)
}

class DeepLinkProcessorChain(
    private val current: DeepLinkProcessor,
    private val next: DeepLinkProcessorChain?
) {
    fun next(router: RouterManager, context: Context, deeplink: Uri) {
        current.process(router, context, deeplink, next)
    }
}