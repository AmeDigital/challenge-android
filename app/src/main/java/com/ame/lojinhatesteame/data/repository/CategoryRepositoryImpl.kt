package com.ame.lojinhatesteame.data.repository

import com.ame.lojinhatesteame.LojinhaApplication
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.executor.disk
import com.ame.lojinhatesteame.data.executor.network
import com.ame.lojinhatesteame.data.executor.ui
import com.ame.lojinhatesteame.data.model.Category
import com.ame.lojinhatesteame.data.remote.ApiService
import com.ame.lojinhatesteame.data.remote.response.BodyResponseCategory
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val apiService: ApiService) : CategoryRepository {

    override fun loadCategory(callbacks: CategoryRepository.CategoryCallbacks) {
        network {
            try {
                val categoryResponse: Response<BodyResponseCategory> = apiService.getCategory().execute()
                if (categoryResponse.isSuccessful) {
                    val responseBody = categoryResponse.body()
                    val categories = mutableListOf<Category>()
                    if (responseBody != null) {
                        disk {
                            responseBody.apply {
                                data.forEach {
                                    categories.add(it)
                                }
                                ui{callbacks.onLoadCategorySuccess(categories)}
                            }
                        }
                    } else {
                        ui{callbacks.onLoadCategoryError(LojinhaApplication.instance.getString(R.string.unexpected_error))}
                    }
                } else {
                    ui{callbacks.onLoadCategoryError(categoryResponse.errorBody().toString())}
                }
            } catch (e : IOException) {
                e.message?.apply {
                    ui{callbacks.onLoadCategoryError(this)}
                }
            }
        }
    }


}