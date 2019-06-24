package com.ame.lojinhatesteame.ui.category

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.ame.lojinhatesteame.data.model.Category
import com.ame.lojinhatesteame.data.repository.CategoryRepository
import com.ame.lojinhatesteame.util.SingleLiveEvent
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository) : ViewModel() {

    var categoryLiveData = MutableLiveData<MutableList<Category>>()
    val isProgressBarVisible = ObservableBoolean()
    val showErrorToast = SingleLiveEvent<String>()


    init {
        isProgressBarVisible.set(false)
    }

    fun onLoadCategories() {
        isProgressBarVisible.set(true)
        categoryRepository.loadCategory(CategoryCallbacks())
    }

    inner class CategoryCallbacks : CategoryRepository.CategoryCallbacks {
        override fun onLoadCategorySuccess(categories: MutableList<Category>) {
            isProgressBarVisible.set(false)
            categoryLiveData.value = categories.toMutableList()
        }

        override fun onLoadCategoryError(errorMessage: String) {
            isProgressBarVisible.set(false)
            showErrorToast.value = errorMessage
        }
    }
}

