package com.iddog.remote.base

import com.lodjinha.remote.LodjinhaService
import com.lodjinha.remote.di.createRemoteModule
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import java.io.File

abstract class BaseTest : KoinTest {

    protected val service: LodjinhaService by inject()
    protected lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        this.configureMockServer()
        this.configureDi()
    }

    @After
    open fun tearDown() {
        this.stopMockServer()
        StandAloneContext.stopKoin()
    }

    private fun configureDi() =
        StandAloneContext.startKoin(listOf(createRemoteModule(mockServer.url("/").toString())))

    private fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    private fun stopMockServer() {
        mockServer.shutdown()
    }

    fun mockHttpResponse(mockServer: MockWebServer, fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName))
    )

    private fun getJson(path: String): String =
        String(File(this.javaClass.classLoader.getResource(path).path).readBytes())
}
