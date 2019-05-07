package br.com.sf.lojinha.ui.product_details

import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.ui.base.BaseNavigator
import br.com.sf.lojinha.ui.base.BasePresenter
import br.com.sf.lojinha.ui.base.BaseView

internal interface ProductDetailContract {

    interface Navigator : BaseNavigator {

        fun goToBack()

    }

    interface View : BaseView<Presenter> {

        fun showProduct(product: Product)

        fun showProductReservedSuccessful()

        fun showProductReserveFailed(message: String)

        fun showProductReserveFailed()

        fun disableReserveButton(disable: Boolean)

    }

    interface Presenter : BasePresenter {

        fun loadProduct(product: Product)

        fun clickReserve()

        fun goToBack()

    }

}