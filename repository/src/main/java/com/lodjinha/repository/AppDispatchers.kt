package com.lodjinha.repository

import kotlinx.coroutines.CoroutineDispatcher

class AppDispatchers(val main: CoroutineDispatcher, val io: CoroutineDispatcher)
