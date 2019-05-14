package br.com.amedigital.lojinha.utils

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

fun RecyclerView.setDefaultHorozontalSettings(context: Context,
                                            adapter: RecyclerView.Adapter<*>)
        : RecyclerView {

    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    this.adapter = adapter

    return this
}

fun RecyclerView.setDefaultVerticalSettings(context: Context,
                                            adapter: RecyclerView.Adapter<*>)
        : RecyclerView {

    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    this.adapter = adapter

    return this
}

fun RecyclerView.setDefaultGridSettings(context: Context,
                                            adapter: RecyclerView.Adapter<*>)
        : RecyclerView {

    this.layoutManager = GridLayoutManager(context, 2)
    this.adapter = adapter

    return this
}