package com.leonardoalves.ametest.utils

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun RequestBuilder<Drawable>.crossFade(): RequestBuilder<Drawable> {
    return transition(DrawableTransitionOptions.withCrossFade())
}