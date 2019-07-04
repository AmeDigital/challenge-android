package br.com.igorfs.lodjinha.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.igorfs.lodjinha.service.CategoryService
import br.com.igorfs.lodjinha.util.RetrofitFactory
import br.com.igorfs.lodjinha.util.callback
import br.com.igorfs.lodjinha.vo.CategoryVo

class CategoryRepository {

    private val categoryList = MutableLiveData<List<CategoryVo>>()
    private val categoryService: CategoryService by lazy {
        RetrofitFactory().getRetrofit().create(CategoryService::class.java)
    }

    companion object {
        private const val TAG = "CategoryRepository"
    }

    fun getCategories(): LiveData<List<CategoryVo>> = categoryList


    fun fetchCategoryList() {
        categoryService.categoryList().enqueue(callback { response, throwable ->
            response?.let {
                response.body()?.let { categoryList.postValue(it.getList) }
            }
            throwable?.let {
                Log.e(TAG, it.message)
            }
        })

    }
}