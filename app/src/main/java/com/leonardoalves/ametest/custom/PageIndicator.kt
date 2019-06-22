package com.leonardoalves.ametest.custom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.leonardoalves.ametest.R

class PageIndicator(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    sealed class Type {
        object Image : Type()
    }

    var types: List<Type>? = null
    var viewPager: ViewPager? = null
        set(value) {
            field = value
            value?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) = select(position)
                override fun onPageScrollStateChanged(state: Int) {}
            })
        }
    var selectedAlpha = 1F
    var unselectedAlpha = 0.6F

    fun init() {
        if (viewPager == null) throw Exception("Variable viewPager must be set.")

        types?.forEach { _ ->
            val resId = R.layout.view_pageindicator_item
            inflate(context, resId, this)

            val child = getChildAt(childCount - 1) as? ViewGroup
                ?: throw Exception("Layout must extend ViewGroup.")
            val background = child.getChildAt(0) as? ImageView
                ?: throw Exception("First child in layout must be an ImageView.")
            background.alpha = unselectedAlpha
        }

        select(0)
    }

    private fun select(position: Int) {
        (0 until childCount).forEach {
            val child = getChildAt(it) as ViewGroup
            val background = child.getChildAt(0) as ImageView
            background.alpha = when (position) {
                it -> selectedAlpha
                else -> unselectedAlpha
            }
        }
    }
}