package com.lodjinha.di

import com.lodjinha.remote.di.createRemoteModule
import com.lodjinha.repository.di.repositoryModule

val appComponent = listOf(
    createRemoteModule("https://alodjinha.herokuapp.com"),
    repositoryModule,
    featureHomeModule,
    featureCategoryModule,
    featureDetailProductModule
)
