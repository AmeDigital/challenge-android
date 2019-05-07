package br.com.sf.lojinha.ui.home

import br.com.sf.lojinha.repository.Category
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.ui.base.BaseNavigator
import br.com.sf.lojinha.ui.base.BasePresenter
import br.com.sf.lojinha.ui.base.BaseView

internal interface HomeContract {

    interface Navigator : BaseNavigator {

        fun goToProductDetail(product: Product)

        fun goToCategory(categoryId: Category)

    }

    interface View : BaseView<Presenter> {

        fun showBestSellers(products: List<Product>)

        fun showCategories(categories: List<Category>)

        fun showBanners(banners: List<String>)

        fun showBestSellersLoading(show: Boolean)

        fun showCategoriesLoading(show: Boolean)
    }

    interface Presenter : BasePresenter {

        fun start()

        fun clickProduct(product: Product)

        fun clickCategory(categoryId: Category)

    }

}