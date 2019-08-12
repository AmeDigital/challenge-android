package com.luizzabuscka.commons.mock

import com.luizzabuscka.commons.models.Banner
import com.luizzabuscka.commons.models.Category

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