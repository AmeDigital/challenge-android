package br.com.sf.lojinha.ui.home

import br.com.sf.lojinha.injection.PerFragment
import br.com.sf.lojinha.repository.Category
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.repository.store.StoreDataSource
import br.com.sf.lojinha.util.schedulers.BaseSchedulerProvider
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@PerFragment
internal class HomePresenter @Inject
constructor(
    private val view: HomeContract.View,
    private val navigator: HomeContract.Navigator,
    private val storeDataSource: StoreDataSource,
    private val schedulerProvider: BaseSchedulerProvider
) : HomeContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun start() {

        view.showBestSellersLoading(true)
        view.showCategoriesLoading(true)

        val bannerDisposable = storeDataSource.listAllBanners()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .flatMap {
                Flowable.fromIterable(it)
                    .map { it.url }
                    .toList()
            }
            .doOnSuccess(view::showBanners)
            .doOnError(Timber::e)
            .subscribe()

        compositeDisposable.add(bannerDisposable)

        val categoriesDisposable = storeDataSource.listAllCategories()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSuccess {
                view.showCategories(it)
                view.showCategoriesLoading(false)
            }
            .doOnError {
                Timber.e(it)
                view.showCategoriesLoading(false)
            }
            .subscribe()

        compositeDisposable.add(categoriesDisposable)

        val bestSellersDisposable = storeDataSource.listBestSellers()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSuccess {
                view.showBestSellers(it)
                view.showBestSellersLoading(false)
            }
            .doOnError {
                Timber.e(it)
                view.showBestSellersLoading(false)
            }
            .subscribe()

        compositeDisposable.add(bestSellersDisposable)
    }

    override fun clickProduct(product: Product) {
        navigator.goToProductDetail(product)
    }

    override fun clickCategory(categoryId: Category) {
        navigator.goToCategory(categoryId)
    }

    override fun destroy() {
        this.compositeDisposable.dispose()
    }
}