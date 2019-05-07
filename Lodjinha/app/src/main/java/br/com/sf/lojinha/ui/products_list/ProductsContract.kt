package br.com.sf.lojinha.ui.products_list

import br.com.sf.lojinha.repository.Category
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.ui.base.BaseNavigator
import br.com.sf.lojinha.ui.base.BasePresenter
import br.com.sf.lojinha.ui.base.BaseView

internal interface ProductsContract {

    interface Navigator : BaseNavigator {

        fun goToProductDetail(product: Product)

        fun goToBack()

    }

    interface View : BaseView<Presenter> {

        fun setTitle(title: String)

        fun showProducts(products: List<Product>)

        fun showLoadingIndicator(show: Boolean)

    }

    interface Presenter : BasePresenter {

        fun loadProducts(category: Category)

        fun loadNextPage()

        fun clickProduct(product: Product)

        fun shouldLoadNextPage(): Boolean

        fun goToBack()

    }

}