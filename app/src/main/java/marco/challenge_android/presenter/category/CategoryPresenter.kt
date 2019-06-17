package marco.challenge_android.presenter.category

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import marco.challenge_android.BuildConfig
import marco.challenge_android.data.LojinhaRepository
import marco.challenge_android.data.LojinhaService

class CategoryPresenter(private val view: CategoryContract.View, private val categoryId: Int? = 0) : CategoryContract.Presenter {

    private val TAG = CategoryPresenter::class.java.name
    private val repository = LojinhaRepository(LojinhaService())

    override fun subscribe() {
        this.getProductsFromCategory(0)
    }

    override fun getProductsFromCategory(offset: Int) {
        addDisposable(repository.getProducts(offset, BuildConfig.LIMIT_OFFSET, categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.setProducts(it.productList)
            }, {
                Log.e(TAG, it.message)
            }))
    }

}