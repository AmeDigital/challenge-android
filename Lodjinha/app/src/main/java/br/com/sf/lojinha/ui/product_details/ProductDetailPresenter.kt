package br.com.sf.lojinha.ui.product_details

import br.com.sf.lojinha.injection.PerFragment
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.repository.store.StoreDataSource
import br.com.sf.lojinha.util.schedulers.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@PerFragment
internal class ProductDetailPresenter @Inject
constructor(
    private val view: ProductDetailContract.View,
    private val navigator: ProductDetailContract.Navigator,
    private val storeDataSource: StoreDataSource,
    private val schedulerProvider: BaseSchedulerProvider
) : ProductDetailContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private var product: Product? = null

    override fun loadProduct(product: Product) {
        this.product = product
        view.showProduct(product)
    }

    override fun clickReserve() {

        product?.run {
            view.disableReserveButton(true)

            val disposable = storeDataSource.reserve(product!!.id)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSuccess {
                    view.disableReserveButton(false)
                    if (!"success".equals(it.result)) {
                        view.showProductReserveFailed(it.message!!)
                    } else {
                        view.showProductReservedSuccessful()
                    }
                }
                .doOnError {
                    Timber.e(it)
                    view.disableReserveButton(false)
                    view.showProductReserveFailed()
                }
                .subscribe()

            compositeDisposable.add(disposable)
        }
    }

    override fun goToBack() {
        navigator.goToBack()
    }

    override fun destroy() {
        this.compositeDisposable.dispose()
    }
}