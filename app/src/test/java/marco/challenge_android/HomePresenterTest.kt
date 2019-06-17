package marco.challenge_android

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Observable
import marco.challenge_android.data.*
import marco.challenge_android.presenter.home.HomeContract
import marco.challenge_android.presenter.home.HomePresenter
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class HomePresenterTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var view: HomeContract.View

    @Mock
    private lateinit var service: LojinhaService

    @Mock
    private lateinit var api: LojinhaAPI

    private lateinit var repository: LojinhaRepository
    private lateinit var presenter: HomeContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = HomePresenter(view)
        repository = LojinhaRepository(service)
    }

    @Test
    fun shouldReturnBannerListWhenPresenterIsSubscribed() {
        val mockResponse = mock(BannerResponse::class.java)

        val bannerList = arrayListOf(
            Banner(1, "url1", "link1"),
            Banner(2, "url2", "link2"),
            Banner(3, "url3", "link3")
        )

        `when`(mockResponse.bannerList).thenReturn(bannerList).thenThrow(RuntimeException())
        `when`(service.getService()).thenReturn(api)
        `when`(repository.getBanners()).thenReturn(Observable.just(mockResponse))

        presenter.getBanners()
        service.getService()
        repository.getBanners()
        view.setBanners(bannerList)

        verify(view).setBanners(bannerList)
    }

    @Test
    fun shouldReturnCategoryListWhenPresenterIsSubscribed() {
        val mockResponse = mock(CategoryResponse::class.java)

        val categoryList = arrayListOf(
            Category(1, "Categoria 1", "url1"),
            Category(2, "Categoria 2", "url2"),
            Category(3, "Categoria 3", "url3")
        )

        `when`(mockResponse.categoryList).thenReturn(categoryList).thenThrow(RuntimeException())
        `when`(service.getService()).thenReturn(api)
        `when`(repository.getCategories()).thenReturn(Observable.just(mockResponse))

        presenter.getCategories()
        service.getService()
        repository.getCategories()
        view.setCategories(categoryList)

        verify(view).setCategories(categoryList)
    }

    @Test
    fun shouldReturnBestSellerProductListWhenPresenterIsSubscribed() {
        val mockResponse = mock(ProductResponse::class.java)

        val productList = arrayListOf(
            Product(1, "Produto 1", "link1", "Descricao prod 1", 1.9, 1.7,
                Category(1, "Cat 1", "link1")),
            Product(2, "Produto 2", "link2", "Descricao prod 2", 2.9, 2.4,
                Category(1, "Cat 1", "link1")),
            Product(3, "Produto 3", "link3", "Descricao prod 3", 12.8, 11.9,
                Category(1, "Cat 1", "link1"))
        )

        `when`(mockResponse.productList).thenReturn(productList).thenThrow(RuntimeException())
        `when`(service.getService()).thenReturn(api)
        `when`(repository.getProductsBestSellers()).thenReturn(Observable.just(mockResponse))

        presenter.getBestSellers()
        service.getService()
        repository.getProductsBestSellers()
        view.setBestSellers(productList)

        verify(view).setBestSellers(productList)
    }

}