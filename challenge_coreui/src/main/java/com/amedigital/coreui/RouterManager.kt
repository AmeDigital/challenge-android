package com.amedigital.coreui

import android.content.Context
import android.net.Uri
import java.lang.StringBuilder

interface RouterManager {
    fun route(context: Context, route: String, parameters: Map<String, String>)
    fun process(context: Context, deepLink: Uri)
}

abstract class DefaultRouterManager : RouterManager {

    abstract val schema: String
    private var deepLinkProcessorChain: DeepLinkProcessorChain? = null

    protected open fun register(processor: DeepLinkProcessor) {
        deepLinkProcessorChain = DeepLinkProcessorChain(processor, deepLinkProcessorChain)
    }

    override fun route(context: Context, route: String, parameters: Map<String, String>) {
        val uri = Uri.parse("$schema://$route?${buildParameters(parameters)}")
        process(context, uri)
    }

    override fun process(context: Context, deepLink: Uri) {
        deepLinkProcessorChain?.next(this, context, deepLink)
    }

    protected open fun buildParameters(parameters: Map<String, String>): String {
        val stringBuilder = StringBuilder()
        var isFirst = true
        for (item in parameters) {
            if (!isFirst) {
                stringBuilder.append("&")
            }
            isFirst = false
            stringBuilder.append("${item.key}=${item.value}")
        }
        return stringBuilder.toString()
    }
}