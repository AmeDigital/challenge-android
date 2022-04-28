package com.amedigital.challenge_home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amedigital.challenge_model.fakeBanners
import com.amedigital.challenge_model.fakeCategorias
import com.amedigital.challenge_model.fakeProdutos
import com.amedigital.challenge_produto.ListaCategorias
import com.amedigital.challenge_produto.ListaProdutos
import com.amedigital.coreui.widgets.Banner

@Composable
fun Home() {
    val homeTag = "HOME"
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Banner(fakeBanners.map { it.linkUrl }, onImageClick = { image ->
            Log.d(homeTag, image)
        })
        ListaCategorias(fakeCategorias, onCategoriaClick = { categoria ->
            Log.d(homeTag, categoria.toString())
        })
        ListaProdutos(fakeProdutos, onProdutoClick = { produto ->
            Log.d(homeTag, produto.toString())
        })
    }
}