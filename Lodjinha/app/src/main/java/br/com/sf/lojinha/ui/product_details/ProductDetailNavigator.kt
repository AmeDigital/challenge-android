package br.com.sf.lojinha.ui.product_details

import br.com.sf.lojinha.injection.PerFragment
import br.com.sf.lojinha.ui.main.MainContract
import javax.inject.Inject

@PerFragment
internal class ProductDetailNavigator @Inject
constructor(private val navigator: MainContract.Navigator) : ProductDetailContract.Navigator {

    override fun goToBack() {
        navigator.goToHome()
    }
}
