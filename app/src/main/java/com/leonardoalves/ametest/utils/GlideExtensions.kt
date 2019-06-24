package com.leonardoalves.ametest.utils

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun RequestBuilder<Drawable>.crossFade(): RequestBuilder<Drawable> {
    return transition(DrawableTransitionOptions.withCrossFade())
}

fun <T> RequestBuilder<T>.centerCrop(): RequestBuilder<T> {
    return apply(RequestOptions().centerCrop())
}