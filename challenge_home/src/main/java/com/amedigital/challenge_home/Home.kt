package com.amedigital.challenge_home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amedigital.challenge_model.fakeCategorias
import com.amedigital.coreui.widgets.Banner

val fakeImages = listOf(
    "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png",
    "https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png",
    "https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png"
)

@Composable
fun Home() {
    val homeTag = "HOME"

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Banner(fakeImages, onImageClick = { image ->
            Log.d(homeTag, image)
        })
        CategoriasWidget(fakeCategorias, onCategoriaClick = { categoria ->
            Log.d(homeTag, categoria.toString())
        })
    }
}