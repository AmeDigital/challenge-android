package com.ame.lojinhatesteame.data.repository

import android.support.annotation.MainThread
import com.ame.lojinhatesteame.data.model.Category

interface CategoryRepository {
    fun loadCategory(callbacks: CategoryCallbacks)

    interface CategoryCallbacks {
        @MainThread
        fun onLoadCategorySuccess(categories: MutableList<Category>)

        @MainThread
        fun onLoadCategoryError(errorMessage: String)
    }
}