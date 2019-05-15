package br.com.amedigital.lojinha.viewmodel

import android.arch.lifecycle.MutableLiveData
import br.com.amedigital.lojinha.api.response.ResultResponse
import br.com.amedigital.lojinha.model.ProdutoResponse
import com.br.cinesky.api.ApiBuilder
import com.br.cinesky.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductListViewModel : BaseViewModel() {

    private var productListResponse = MutableLiveData<ProdutoResponse>()
    fun productListRepositoryResponse(): MutableLiveData<ProdutoResponse> = productListResponse

    private var productResponse = MutableLiveData<ResultResponse>()
    fun productRepositoryResponse(): MutableLiveData<ResultResponse> = productResponse

    fun loadRepositories(id: String) {
        ApiBuilder.getWebService().getProduto(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doFinally { showLoading(false) }
            .subscribe({
                productListResponse.value = it
            }, {
                showFailure(it)
            })
    }

    fun setRepositories(id: String) {
        ApiBuilder.getWebService().setProdutoPorId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doFinally { showLoading(false) }
            .subscribe({
                productResponse.value = it
            }, {
                showFailure(it)
            })
    }
}