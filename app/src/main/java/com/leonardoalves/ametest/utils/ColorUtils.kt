package com.leonardoalves.ametest.utils

import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat

object ColorUtils {
    fun parse(context: Context, color: Int) = ContextCompat.getColor(context, color)
    fun stateListOf(color: Int): ColorStateList = ColorStateList.valueOf(color)
}