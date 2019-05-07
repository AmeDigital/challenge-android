package br.com.sf.lojinha.ui.main

import br.com.sf.lojinha.repository.Category
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.ui.base.BaseNavigator
import br.com.sf.lojinha.ui.base.BasePresenter
import br.com.sf.lojinha.ui.base.BaseView

interface MainContract {

    interface Navigator : BaseNavigator {

        enum class State {
            SINGLE_COLUMN_MASTER, SINGLE_COLUMN_DETAILS, TWO_COLUMNS_EMPTY, TWO_COLUMNS_WITH_DETAILS
        }

        fun goToHome()

        fun goToAbout()

        fun goToProductDetail(product: Product)

        fun goToCategory(categoryId: Category)

        fun onBackPressed(): Boolean

    }

    interface View : BaseView<Presenter> {


        fun highlightHome()

        fun highlightAbout()

        fun closeDrawer()

        fun openDrawer()

        fun toggleDrawer()

    }

    interface Presenter : BasePresenter {

        fun clickHome()

        fun clickAbout()

    }

}
