package br.com.amedigital.lojinha.viewmodel

import android.arch.lifecycle.MutableLiveData
import br.com.amedigital.lojinha.model.CategoriaResponse
import br.com.amedigital.lojinha.model.MaisVendidoResponse
import com.br.cinesky.api.ApiBuilder
import com.br.cinesky.base.BaseViewModel
import com.br.cinesky.model.BannerResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : BaseViewModel() {
    private var bannerResponse = MutableLiveData<BannerResponse>()
    private var categoryResponse = MutableLiveData<CategoriaResponse>()
    private var bestSellerResponse = MutableLiveData<MaisVendidoResponse>()

    fun bannerRepositoryResponse(): MutableLiveData<BannerResponse> = bannerResponse
    fun categoryRepositoryResponse(): MutableLiveData<CategoriaResponse> = categoryResponse
    fun bestSellerRepositoryResponse(): MutableLiveData<MaisVendidoResponse> = bestSellerResponse

    fun loadRepositories() {
        ApiBuilder.getWebService().getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doFinally { showLoading(false) }
            .subscribe({
                bannerResponse.value = it
            }, {
                showFailure(it)
            })
    }


    fun loadCategorys() {
        ApiBuilder.getWebService().getCategoria()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doFinally { showLoading(false) }
            .subscribe({
                categoryResponse.value = it
            }, {
                showFailure(it)
            })
    }

    fun loadBestSelers() {
        ApiBuilder.getWebService().getMaisVendidos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doFinally { showLoading(false) }
            .subscribe({
                bestSellerResponse.value = it
            }, {
                showFailure(it)
            })
    }
}