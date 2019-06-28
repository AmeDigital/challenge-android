package com.ame.lojinhatesteame.data.repository


import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.ame.lojinhatesteame.data.model.Banner
import com.ame.lojinhatesteame.data.remote.ApiService
import com.ame.lojinhatesteame.data.remote.response.BodyResponseBanner
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class BannerRepositoryImplTest {
    @Rule
    @JvmField
    var instantExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var call: Call<BodyResponseBanner>

    @Mock
    private lateinit var callbacks: BannerRepository.BannerCallbacks

    @InjectMocks
    private lateinit var repository: BannerRepositoryImpl

    val banner = Banner().apply {
        id = 1
        urlImage = "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png"
        linkUrl = "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png"
    }
    val banners = ArrayList<Banner>().apply { add(banner) }
    private val messageError = "Authorization Required"

    @Test
    fun bannerApiCall() {
        `when`(apiService.getBanner()).thenReturn(call)
        `when`(call.execute()).thenReturn(Response.success(BodyResponseBanner(banners)))

        repository.loadBanner(callbacks)

        verify(apiService, after(5).atMost(4)).getBanner()
        verify(call, after(5).atMost(4)).execute()

        verifyNoMoreInteractions(apiService)
        verifyNoMoreInteractions(call)
    }

    @Test
    fun bannerSuccessCallback() {
        `when`(apiService.getBanner()).thenReturn(call)
        `when`(call.execute()).thenReturn(Response.success(BodyResponseBanner(banners)))

        repository.loadBanner(callbacks)
        verify(callbacks, after(5).atMost(4)).onLoadBannerSuccess(banners.toMutableList())
    }

    @Test
    fun bannerErrorCallback() {
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val body = ResponseBody.create(mediaType, "Unauthorized")

        val response = Response.error<BodyResponseBanner>(401, body)

        `when`(apiService.getBanner()).thenReturn(call)
        `when`(call.execute()).thenReturn(response)

        repository.loadBanner(callbacks)
        verify(callbacks, after(5).atMost(4)).onLoadBannerError(messageError)
    }
}