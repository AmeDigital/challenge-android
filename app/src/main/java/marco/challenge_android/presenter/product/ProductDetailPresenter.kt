package marco.challenge_android.presenter.product

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import marco.challenge_android.data.LojinhaRepository
import marco.challenge_android.data.LojinhaService

class ProductDetailPresenter(val view: ProductDetailContract.View) : ProductDetailContract.Presenter {

    private val repository = LojinhaRepository(LojinhaService())
    override fun subscribe() {}
    override fun reserveProduct(productId: Int?) {
        addDisposable(repository.reserveProduct(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.reserveProductStatus(true)
            }, {
                view.reserveProductStatus(false)
            })
        )
    }

}