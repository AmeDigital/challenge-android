package marco.challenge_android.presenter.product

import marco.challenge_android.presenter.BasePresenter
import marco.challenge_android.presenter.BaseView

interface ProductDetailContract {

    interface View : BaseView<Presenter> {
        fun reserveProductStatus(status: Boolean)
    }

    interface Presenter : BasePresenter {
        fun reserveProduct(productId: Int?)
    }

}