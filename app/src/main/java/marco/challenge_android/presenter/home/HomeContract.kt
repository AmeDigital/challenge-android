package marco.challenge_android.presenter.home

import marco.challenge_android.data.Banner
import marco.challenge_android.data.Category
import marco.challenge_android.data.Product
import marco.challenge_android.presenter.BasePresenter
import marco.challenge_android.presenter.BaseView

interface HomeContract {

    interface Presenter : BasePresenter {
        fun getBanners()
        fun getCategories()
        fun getBestSellers()
    }

    interface View : BaseView<Presenter> {
        fun setBanners(banners: List<Banner>)
        fun setCategories(categories: List<Category>)
        fun setBestSellers(bestSellers: List<Product>)
    }
}