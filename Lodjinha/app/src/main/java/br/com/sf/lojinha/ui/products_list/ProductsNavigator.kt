package br.com.sf.lojinha.ui.products_list

import br.com.sf.lojinha.injection.PerFragment
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.ui.main.MainContract
import javax.inject.Inject

@PerFragment
internal class ProductsNavigator @Inject
constructor(private val navigator: MainContract.Navigator) : ProductsContract.Navigator {

    override fun goToProductDetail(product: Product) {
        navigator.goToProductDetail(product)
    }

    override fun goToBack() {
        navigator.goToHome()
    }
}
