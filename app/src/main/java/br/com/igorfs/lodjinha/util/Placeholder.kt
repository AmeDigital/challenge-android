package br.com.igorfs.lodjinha.util

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun getLoaderPlaceholder(context: Context) = CircularProgressDrawable(context)
    .also {
        it.strokeWidth = 5f
        it.centerRadius = 30f
        it.start()
    }

