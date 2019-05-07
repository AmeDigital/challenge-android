package br.com.sf.lojinha.ui.home

import br.com.sf.lojinha.injection.PerFragment
import br.com.sf.lojinha.repository.Category
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.ui.main.MainContract
import javax.inject.Inject

@PerFragment
internal class HomeNavigator @Inject
constructor(private val navigator: MainContract.Navigator) : HomeContract.Navigator {

    override fun goToProductDetail(product: Product) {
        navigator.goToProductDetail(product)
    }

    override fun goToCategory(categoryId: Category) {
        navigator.goToCategory(categoryId)
    }
}
