package br.com.sf.lojinha.ui.products_list

import br.com.sf.lojinha.injection.PerFragment
import br.com.sf.lojinha.repository.Category
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.repository.store.StoreDataSource
import br.com.sf.lojinha.util.schedulers.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@PerFragment
internal class ProductsPresenter @Inject
constructor(
    private val view: ProductsContract.View,
    private val navigator: ProductsContract.Navigator,
    private val storeDataSource: StoreDataSource,
    private val schedulerProvider: BaseSchedulerProvider
) : ProductsContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private var category: Category? = null

    private var offset = 0

    private var isLoading = false

    private var hasLoadedAll = false

    override fun loadProducts(category: Category) {
        this.category = category

        view.setTitle(category.description)
        loadNextPage()
    }

    override fun loadNextPage() {
        setLoading(true)

        val disposable = storeDataSource.findProducts(offset, 20, category!!.id)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSuccess {
                view.showProducts(it)
                setLoading(false)
                incOffset()
                verifyHasLoadedAllContent(it)
            }
            .doOnError {
                Timber.e(it)
                setLoading(false)
            }
            .subscribe()

        compositeDisposable.add(disposable)
    }

    private fun verifyHasLoadedAllContent(content: List<Product>) {
        if (content.isEmpty()) {
            hasLoadedAll = true
        }
    }

    private fun incOffset() {
        offset += 20
    }

    private fun setLoading(loading: Boolean) {
        view.showLoadingIndicator(loading)
        isLoading = loading
    }

    override fun shouldLoadNextPage(): Boolean {
        return !isLoading && !hasLoadedAll
    }

    override fun clickProduct(product: Product) {
        navigator.goToProductDetail(product)
    }

    override fun goToBack() {
        navigator.goToBack()
    }

    override fun destroy() {
        this.compositeDisposable.dispose()
    }
}