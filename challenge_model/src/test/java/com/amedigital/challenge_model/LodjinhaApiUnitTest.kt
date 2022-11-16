package com.amedigital.challenge_model

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Test

import org.junit.Assert.*


class LodjinhaApiUnitTest : LodjinhaApiUnitBaseTest() {

    @Test
    fun `should hit endpoints with expected parameters for getProdutos`() = runBlocking {
        // given
        val body = super.readFile("produtos.json")
        super.server.enqueue(MockResponse().setBody(body))
        // when
        super.api.getProdutos()
        val request = super.server.takeRequest()
        // then
        assertEquals(request.path,"/produto")
    }

    @Test
    fun `should getProdutos returns expected minimal data`() = runBlocking {
        // given
        val body = super.readFile("produtos.json")
        super.server.enqueue(MockResponse().setBody(body))
        // when
        val result = super.api.getProdutos()
        // then
        assertNotNull(result.data)
        assertEquals(result.data.size,25)
        assertEquals(result.offset,0)
        assertEquals(result.total,25)
        assertEquals(result.data[0].id,1)
        assertEquals(result.data[0].nome,"Game Horizon Zero Down")
        assertEquals(result.data[0].urlImagem,"https://images-submarino.b2w.io/produtos/01/00/item/130836/1/130836199P1.jpg")
        assertEquals(result.data[0].descricao,"Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!")
        assertEquals(result.data[0].precoDe,299.0, 0.0)
        assertEquals(result.data[0].precoPor,119.9899999999999948840923025272786617279052734375, 0.0)
        assertNotNull(result.data[0].categoria)
        assertEquals(result.data[0].categoria.id,1)
        assertEquals(result.data[0].categoria.descricao,"Games")
        assertEquals(result.data[0].categoria.urlImagem,"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png")
    }

    @Test
    fun `should hit endpoints with expected parameters for getProduto`() = runBlocking {
        // given
        val body = super.readFile("produtos.json")
        super.server.enqueue(MockResponse().setBody(body))
        // when
        super.api.getProduto(7)
        val request = super.server.takeRequest()
        // then
        assertEquals(
            request.path,
            "/produto/7"
        )
    }

    @Test
    fun `should hit endpoints with expected parameters for maisVendidos`() = runBlocking {
        // given
        val body = super.readFile("produtos.json")
        super.server.enqueue(MockResponse().setBody(body))
        // when
        super.api.getMaisVendidos()
        val request = super.server.takeRequest()
        // then
        assertEquals(
            request.path,
            "/produto/maisvendidos"
        )
    }
}