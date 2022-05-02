package com.amedigital.challenge_home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amedigital.challenge_model.api.ApiDataResponse
import com.amedigital.challenge_model.api.LodjinhaApi
import com.amedigital.challenge_model.fakeBanners
import com.amedigital.challenge_model.fakeCategorias
import com.amedigital.challenge_model.fakeProdutos
import com.amedigital.challenge_model.repositories.BannerRepository
import com.amedigital.challenge_model.repositories.BannerRepositoryImpl
import com.amedigital.challenge_model.repositories.CategoriaRepository
import com.amedigital.challenge_model.repositories.CategoriaRepositoryImpl
import com.amedigital.challenge_model.repositories.ProdutoRepository
import com.amedigital.challenge_model.repositories.ProdutoRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.inject
import org.koin.core.module.Module
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class HomeTest : KoinBaseTest() {

    override fun getKoinModules(): Module = module(override = true) {
        single { mockk<LodjinhaApi>(relaxed = true) }
        single<BannerRepository> { BannerRepositoryImpl(get()) }
        single<CategoriaRepository> { CategoriaRepositoryImpl(get()) }
        single<ProdutoRepository> { ProdutoRepositoryImpl(get()) }
        viewModel { HomeViewModel(get(), get(), get()) }
    }

    private val api by inject<LodjinhaApi>()

    @get:Rule
    var composeTestRule = createComposeRule()

    fun setupMocks(){
        coEvery {
            api.getBanners(
            )
        } returns ApiDataResponse(fakeBanners, 0, fakeBanners.size)
        coEvery {
            api.getCategorias(
            )
        } returns ApiDataResponse(fakeCategorias, 0, fakeCategorias.size)
        coEvery {
            api.getMaisVendidos(
            )
        } returns ApiDataResponse(fakeProdutos, 0, fakeProdutos.size)
    }

    @Test
    fun Given_MocksApi_WhenShowHome_DisplayTexts() {
        // given
        setupMocks()
        // when
        composeTestRule.setContent {
            Home()
        }
        // then
        composeTestRule.onNodeWithText("Categorias").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mais vendidos").assertIsDisplayed()
    }
}