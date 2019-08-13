package com.luizzabuscka.commons.mock

import com.luizzabuscka.commons.model.Banner
import com.luizzabuscka.commons.model.Category
import com.luizzabuscka.commons.model.Product

fun mockBanners() : List<Banner> {
    return listOf(
        Banner("https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png", "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png"),
        Banner("https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png", "https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png"),
        Banner("https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png", "https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png")
    )
}

fun mockCategories() : List<Category> {
    return listOf(
        Category(1, "Games", "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"),
        Category(2, "Livros", "http://4.bp.blogspot.com/-6Bta1H9d22g/UJAIJbqcHhI/AAAAAAAAKi4/hvgjWrlFc64/s1600/resenha-missiologia.png"),
        Category(3, "Celulares", "http://pt.seaicons.com/wp-content/uploads/2015/11/Mobile-Smartphone-icon.png"),
        Category(4, "Informática", "http://portal.ifrn.edu.br/campus/ceara-mirim/noticias/ifrn-oferece-curso-de-informatica-basica-para-pais-dos-estudantes/image_preview"),
        Category(5, "Eletrodoméstico", "http://classificados.folharegiao.com.br/files/classificados_categoria/photo/8/sm_4d5ed3beb0f31b61cb9a01e46ecd0cf9.png"),
        Category(6, "TVs", "http://i.utdstc.com/icons/256/terrarium-tv-android.png"),
        Category(7, "Filmes e Séries", "https://pbs.twimg.com/profile_images/801033586438733824/91Y_N91t_reasonably_small.jpg"),
        Category(8, "Móveis e Decorações", "https://image.flaticon.com/icons/png/128/148/148188.png"),
        Category(9, "Moda, Beleza e Perfumaria", "http://icon-icons.com/icons2/196/PNG/128/fashion_23852.png"),
        Category(10, "Papelaria", "http://esen.pt/in/images/stories/skills_256.png")
    )
}

fun mockProductsBestSellers() : List<Product> {
    return listOf(
        Product(7, "Fifa 17",
            "https://images-submarino.b2w.io/produtos/01/00/item/128926/4/128926443_1GG.png",
            "Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!",
            "299,00",
            "199,99",
            mockCategories()[0]
        ),
        Product(11, "Forza Horizon 3",
            "https://images-submarino.b2w.io/produtos/01/00/item/128831/2/128831209_1GG.jpg",
            "Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.<br/><br/>A população ela precisa da Zona Franca de Manaus, porque na Zona franca de Manaus, não é uma zona de exportação, é uma zona para o Brasil. Portanto ela tem um objetivo, ela evita o desmatamento, que é altamente lucravito. Derrubar arvores da natureza é muito lucrativo!<br/><br/>No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.<br/><br/>",
            "299,00",
            "199,99",
            mockCategories()[0]
        ),
        Product(11, "Forza Horizon 3",
            "https://images-submarino.b2w.io/produtos/01/00/item/128831/2/128831209_1GG.jpg",
            "Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.<br/><br/>A população ela precisa da Zona Franca de Manaus, porque na Zona franca de Manaus, não é uma zona de exportação, é uma zona para o Brasil. Portanto ela tem um objetivo, ela evita o desmatamento, que é altamente lucravito. Derrubar arvores da natureza é muito lucrativo!<br/><br/>No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.<br/><br/>",
            "299,00",
            "199,99",
            mockCategories()[0]
        ),
        Product(11, "Forza Horizon 3",
            "https://images-submarino.b2w.io/produtos/01/00/item/128831/2/128831209_1GG.jpg",
            "Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.<br/><br/>A população ela precisa da Zona Franca de Manaus, porque na Zona franca de Manaus, não é uma zona de exportação, é uma zona para o Brasil. Portanto ela tem um objetivo, ela evita o desmatamento, que é altamente lucravito. Derrubar arvores da natureza é muito lucrativo!<br/><br/>No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.<br/><br/>",
            "299,00",
            "199,99",
            mockCategories()[0]
        ),
        Product(11, "Forza Horizon 3",
            "https://images-submarino.b2w.io/produtos/01/00/item/128831/2/128831209_1GG.jpg",
            "Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.<br/><br/>A população ela precisa da Zona Franca de Manaus, porque na Zona franca de Manaus, não é uma zona de exportação, é uma zona para o Brasil. Portanto ela tem um objetivo, ela evita o desmatamento, que é altamente lucravito. Derrubar arvores da natureza é muito lucrativo!<br/><br/>No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.<br/><br/>",
            "299,00",
            "199,99",
            mockCategories()[0]
        ),
        Product(11, "Forza Horizon 3",
            "https://images-submarino.b2w.io/produtos/01/00/item/128831/2/128831209_1GG.jpg",
            "Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.<br/><br/>A população ela precisa da Zona Franca de Manaus, porque na Zona franca de Manaus, não é uma zona de exportação, é uma zona para o Brasil. Portanto ela tem um objetivo, ela evita o desmatamento, que é altamente lucravito. Derrubar arvores da natureza é muito lucrativo!<br/><br/>No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.<br/><br/>",
            "299,00",
            "199,99",
            mockCategories()[0]
        )
    )
}