package com.amedigital.challenge_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Categoria(
    val id: Int,
    val descricao: String,
    val urlImagem: String
) : Parcelable

val fakeCategorias = listOf(
    Categoria(
        1,
        "Games",
        "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
    ),
    Categoria(
        2,
        "Livros",
        "http://4.bp.blogspot.com/-6Bta1H9d22g/UJAIJbqcHhI/AAAAAAAAKi4/hvgjWrlFc64/s1600/resenha-missiologia.png"
    ),
    Categoria(
        3,
        "Celulares",
        "http://pt.seaicons.com/wp-content/uploads/2015/11/Mobile-Smartphone-icon.png"
    ),
    Categoria(
        4,
        "Informática",
        "http://portal.ifrn.edu.br/campus/ceara-mirim/noticias/ifrn-oferece-curso-de-informatica-basica-para-pais-dos-estudantes/image_preview"
    ),
    Categoria(
        5,
        "Eletrodoméstico",
        "http://classificados.folharegiao.com.br/files/classificados_categoria/photo/8/sm_4d5ed3beb0f31b61cb9a01e46ecd0cf9.png"
    ),
    Categoria(
        6,
        "TVs",
        "http://i.utdstc.com/icons/256/terrarium-tv-android.png"
    ),
    Categoria(
        7,
        "Filmes e Séries",
        "https://pbs.twimg.com/profile_images/801033586438733824/91Y_N91t_reasonably_small.jpg"
    ),
    Categoria(
        8,
        "Móveis e Decorações",
        "https://image.flaticon.com/icons/png/128/148/148188.png"
    ),
    Categoria(
        9,
        "Moda, Beleza e Perfumaria",
        "http://icon-icons.com/icons2/196/PNG/128/fashion_23852.png"
    ),
    Categoria(
        10,
        "Papelaria",
        "http://esen.pt/in/images/stories/skills_256.png"
    )
)