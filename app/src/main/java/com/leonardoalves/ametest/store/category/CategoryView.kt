package com.leonardoalves.ametest.store.category

import com.leonardoalves.ametest.custom.ViewModel

interface CategoryView {
    fun addItems(items: List<ViewModel>, resetList: Boolean = false)
    fun setupToolbar(title: String)
    fun startLoading()
    fun stopLoading()
}