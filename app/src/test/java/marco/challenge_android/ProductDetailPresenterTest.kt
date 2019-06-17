package marco.challenge_android

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Observable
import marco.challenge_android.data.LojinhaAPI
import marco.challenge_android.data.LojinhaRepository
import marco.challenge_android.data.LojinhaService
import marco.challenge_android.presenter.product.ProductDetailContract
import marco.challenge_android.presenter.product.ProductDetailPresenter
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ProductDetailPresenterTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var view: ProductDetailContract.View

    @Mock
    private lateinit var service: LojinhaService

    @Mock
    private lateinit var api: LojinhaAPI

    private lateinit var repository: LojinhaRepository
    private lateinit var presenter: ProductDetailContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = ProductDetailPresenter(view)
        repository = LojinhaRepository(service)
    }

    @Test
    fun shouldReturnTrueIfProductIsReserved() {
        `when`(service.getService()).thenReturn(api)
        `when`(repository.reserveProduct(1)).thenReturn(Observable.empty())

        presenter.reserveProduct(1)
        service.getService()
        repository.reserveProduct(1)

        verify(view, times(1)).reserveProductStatus(true)
    }

    @Test
    fun shouldReturnFalseIfProductIsNotReserved() {
        `when`(service.getService()).thenReturn(api)
        `when`(repository.reserveProduct(1)).thenReturn(Observable.error(Throwable()))

        presenter.reserveProduct(1)
        service.getService()
        repository.reserveProduct(1)
        view.reserveProductStatus(false)

        verify(view, times(1)).reserveProductStatus(false)
    }

}