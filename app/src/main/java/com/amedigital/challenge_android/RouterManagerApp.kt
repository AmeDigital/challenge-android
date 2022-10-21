package com.amedigital.challenge_android

import com.amedigital.coreui.DefaultRouterManager

class RouterManagerApp : DefaultRouterManager() {

    companion object {
        const val LODJINHA_SCHEMA = "lodjinha"
    }

    override val schema: String
        get() = LODJINHA_SCHEMA

    init {
        routerProcessors().map { processor ->
            register(processor)
        }
    }
}