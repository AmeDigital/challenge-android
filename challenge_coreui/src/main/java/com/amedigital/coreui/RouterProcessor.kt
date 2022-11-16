package com.amedigital.coreui

import android.content.Context
import android.content.Intent
import android.net.Uri

interface RouterProcessor {
    fun process(
        router: RouterManager,
        context: Context,
        uri: Uri,
        chain: RouterProcessorChain?
    )
}

fun RouterProcessor.start(context: Context, uri: Uri) {
    val intent = Intent(uri.host, uri)
    context.startActivity(intent)
}

class RouterProcessorChain(
    private val current: RouterProcessor,
    private val next: RouterProcessorChain?
) {
    fun next(router: RouterManager, context: Context, uri: Uri) {
        current.process(router, context, uri, next)
    }
}