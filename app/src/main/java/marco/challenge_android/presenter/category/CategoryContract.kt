package marco.challenge_android.presenter.category

import marco.challenge_android.data.Product
import marco.challenge_android.presenter.BasePresenter
import marco.challenge_android.presenter.BaseView

interface CategoryContract {

    interface View : BaseView<Presenter> {
        fun setProducts(products: List<Product>?)
    }

    interface Presenter : BasePresenter {
        fun getProductsFromCategory(offset: Int)
    }

}