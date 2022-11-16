package com.amedigital.challenge_produto

import android.content.Intent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amedigital.challenge_model.api.ApiDataResponse
import com.amedigital.challenge_model.api.LodjinhaApi
import com.amedigital.challenge_model.fakeProdutos
import com.amedigital.challenge_model.repositories.BannerRepository
import com.amedigital.challenge_model.repositories.BannerRepositoryImpl
import com.amedigital.challenge_model.repositories.CategoriaRepository
import com.amedigital.challenge_model.repositories.CategoriaRepositoryImpl
import com.amedigital.challenge_model.repositories.ProdutoRepository
import com.amedigital.challenge_model.repositories.ProdutoRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.model.Statement
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.inject
import org.koin.core.module.Module
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class ProdutoTest : KoinBaseTest() {

    override fun getKoinModules(): Module = module(override = true) {
        single { mockk<LodjinhaApi>(relaxed = true) }
        single<ProdutoRepository> { ProdutoRepositoryImpl(get()) }
        viewModel { ProdutoViewModel(get(), get()) }
    }

    private val api by inject<LodjinhaApi>()

    @get:Rule
    val rule = createEmptyComposeRule()

    fun setupMocks() {
        val anyValue = slot<Int>()
        coEvery {
            api.getProduto(
                capture(anyValue)
            )
        } returns fakeProdutos.first()
    }

    @Test
    fun Given_MocksApi_WhenShowHome_DisplayTexts() {
        // given
        setupMocks()
        val produto = fakeProdutos.first()
        // when
        ActivityScenario.launch<ProdutoActivity>(
            Intent(ApplicationProvider.getApplicationContext(), ProdutoActivity::class.java).apply {
                putExtra(ProdutoActivity.PARAM_PRODUTO, produto)
            }
        ).use {
            // then
            rule.onNode(hasText(produto.nome)).assertExists()
            rule.onNode(hasText(produto.descricao)).assertExists()
        }
    }
}