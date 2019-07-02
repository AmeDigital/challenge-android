package com.lodjinha.detailProduct

import androidx.lifecycle.LiveData
import com.lodjinha.repository.LodjinhaRepository
import com.lodjinha.repository.utils.Resource
import okhttp3.ResponseBody

class ReserveProductUseCase(private val repository: LodjinhaRepository) {

    suspend operator fun invoke(productId: Int): LiveData<Resource<ResponseBody>> =
        repository.reserveProductAsync(productId)
}
