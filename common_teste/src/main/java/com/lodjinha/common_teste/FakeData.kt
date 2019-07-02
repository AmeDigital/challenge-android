package com.lodjinha.common_teste

import com.lodjinha.model.*

object FakeData {

    fun createFakeBanners(): Banners = Banners(
        data = listOf(
            Banner(
                id = 1,
                linkUrl = "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png",
                urlImage = "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png"
            ),
            Banner(
                id = 2,
                linkUrl = "https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png",
                urlImage = "https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png"
            )
        )
    )

    fun createFakeCategories(): Categories = Categories(
        data = listOf(
            Category(
                id = 1,
                description = "Games",
                urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
            ),
            Category(
                id = 2,
                description = "Livros",
                urlImage = "http://4.bp.blogspot.com/-6Bta1H9d22g/UJAIJbqcHhI/AAAAAAAAKi4/hvgjWrlFc64/s1600/resenha-missiologia.png"
            )
        )
    )

    fun createFakeProducts(): Products = Products(
        data = listOf(
            Product(
                id = 1,
                name = "Game Horizon Zero Down",
                urlImage = "https://images-submarino.b2w.io/produtos/01/00/item/130836/1/130836199P1.jpg",
                description = "Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!",
                originalPrice = 299.0,
                currentPrice = 119.9899999999999948840923025272786617279052734375,
                category = Category(
                    id = 1,
                    description = "Games",
                    urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
                )
            ),
            Product(
                id = 1,
                name = "NBA2K17",
                urlImage = "https://images-submarino.b2w.io/produtos/01/00/item/128815/8/128815807_1GG.jpg",
                description = "Test description",
                originalPrice = 299.0,
                currentPrice = 119.9899999999999948840923025272786617279052734375,
                category = Category(
                    id = 1,
                    description = "Games",
                    urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
                )
            )
        ),
        offset = 0,
        total = 0
    )
}
