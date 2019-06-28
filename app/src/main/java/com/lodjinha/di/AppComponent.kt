package com.lodjinha.di

import com.lodjinha.remote.di.createRemoteModule

val appComponent = listOf(
    createRemoteModule("https://alodjinha.herokuapp.com/")
)
