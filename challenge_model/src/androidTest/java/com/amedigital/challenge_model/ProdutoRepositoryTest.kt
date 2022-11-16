package com.amedigital.challenge_model

import com.amedigital.challenge_model.api.ApiDataResponse
import com.amedigital.challenge_model.api.LodjinhaApi
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.repositories.ProdutoRepository
import com.amedigital.challenge_model.repositories.ProdutoRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.test.assertTrue

class ProdutoRepositoryTest : KoinBaseTest() {

    override fun getKoinModules(): Module = module(override = true) {
        single { mockk<LodjinhaApi>(relaxed = true) }
        single<ProdutoRepository> { ProdutoRepositoryImpl(get()) }
    }

    private val api by inject<LodjinhaApi>()
    private val repository by inject<ProdutoRepository>()

    @Test
    fun givenResourceProdutos_whenGetProdutos_thenReturnsSuccessfully() = runBlocking {
        // given
        coEvery {
            api.getProdutos(
            )
        } returns ApiDataResponse(fakeProdutos, 0, fakeProdutos.size)
        // when
        val result = repository.getProdutos()
        // then
        assertTrue(result is Resource.Success)
    }

    @Test
    fun givenResourceProduto_whenGetProduto_thenReturnsSuccessfully() = runBlocking {
        // given
        val anyValue = slot<Int>()
        coEvery {
            api.getProduto(
                capture(anyValue)
            )
        } returns fakeProdutos.first()
        // when
        val result = repository.getProduto(123)
        // then
        assertTrue(result is Resource.Success)
    }

    @Test
    fun givenResourceProduto_whenGetProdutoFault_thenReturnsThrowable() = runBlocking {
        // given
        val anyValue = slot<Int>()
        coEvery {
            api.getProduto(
                capture(anyValue)
            )
        } throws Exception()
        // when
        val result = repository.getProduto(123)
        // then
        assertTrue(result is Resource.Failure)
    }
}