package br.com.amedigital.banner

import br.com.amedigital.network.model.BodyRequestCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriaPresenter constructor (var call: Call<BodyRequestCategory>, var view: CategoriaContract.View) : CategoriaContract.CategoryActionListener {

    companion object {
        const val ERROR_LIST = "lista vazia"
       const val ERROR_DEFAULT = "ocorreu um erro"
    }

    override fun loadCategories() {
        view.setProgressIndicatorCategory(true)
        call.enqueue(object : Callback<BodyRequestCategory> {

            override fun onResponse(call: Call<BodyRequestCategory>, response: Response<BodyRequestCategory>) {
                view.setProgressIndicatorCategory(false)
                if (response.isSuccessful) {
                    response.body()?.apply {
                        view.showCategories(data)
                    } ?: kotlin.run {
                        view.showErrorCategory(ERROR_LIST)
                    }
                } else {
                    view.showErrorCategory(ERROR_DEFAULT)
                }
            }

            override fun onFailure(call: Call<BodyRequestCategory>, t: Throwable) {
                view.setProgressIndicatorCategory(false)
                t.message?.apply {
                    view.showErrorCategory(this)
                }
            }
        })
    }
}