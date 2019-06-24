package com.ame.lojinhatesteame.ui.banner

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.ame.lojinhatesteame.data.model.Banner
import com.ame.lojinhatesteame.data.repository.BannerRepository
import com.ame.lojinhatesteame.util.any
import com.ame.lojinhatesteame.util.capture
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BannerViewModelTest {
    @Rule
    @JvmField
    val taskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var viewModel: BannerViewModel

    @Mock
    private lateinit var bannerRepository: BannerRepository

    @Captor
    private lateinit var bannerCallbacks: ArgumentCaptor<BannerRepository.BannerCallbacks>

    @Test
    fun callsBannerRepository() {
        viewModel.onLoadBanners()
        verify(bannerRepository).loadBanner(any())
    }

    @Test
    fun showsProgressBar() {
        viewModel.onLoadBanners()
        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(true))
    }

    @Test
    fun progressBarIsInvisibleInitially() {
        MatcherAssert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
    }

    @Test
    fun onLoadBannerSuccess() {
        val banner = Banner()
        val banners = ArrayList<Banner>()
        with(banner) {
            id = 7
            urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
        }
        banners.add(banner)

        viewModel.onLoadBanners()
        verify(bannerRepository).loadBanner(capture(bannerCallbacks))
        bannerCallbacks.value.onLoadBannerSuccess(banners.toMutableList())

        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
        Assert.assertEquals(viewModel.bannerLiveData.value?.get(0)?.id, banners[0].id)
    }

    @Test
    fun onLoadBannerError() {
        val messageError = "erro ao carregar banner"

        viewModel.onLoadBanners()
        verify(bannerRepository).loadBanner(capture(bannerCallbacks))
        bannerCallbacks.value.onLoadBannerError(messageError)

        Assert.assertThat(viewModel.isProgressBarVisible.get(), CoreMatchers.`is`(false))
        Assert.assertEquals(viewModel.showErrorToast.value, messageError)
    }
}