package marco.challenge_android.presenter.home

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import marco.challenge_android.data.LojinhaRepository
import marco.challenge_android.data.LojinhaService

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {

    private val TAG = HomePresenter::class.java.name
    private val repository = LojinhaRepository(LojinhaService())

    override fun subscribe() {
        this.getBanners()
        this.getCategories()
        this.getBestSellers()
    }

    override fun getBanners() {
        addDisposable(repository.getBanners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.bannerList?.let { banners ->
                    view.setBanners(banners)
                }
            }, {
                Log.e(TAG, it.message)
            })
        )
    }

    override fun getCategories() {
        addDisposable(repository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.categoryList?.let { categories ->
                    view.setCategories(categories)
                }
            }, {
                Log.e(TAG, it.message)
            })
        )
    }

    override fun getBestSellers() {
        addDisposable(repository.getProductsBestSellers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.productList?.let { products ->
                    view.setBestSellers(products)
                }
            }, {
                Log.e(TAG, it.message)
            })
        )
    }

}