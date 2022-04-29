package com.amedigital.challenge_home

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.fakeBanners
import com.amedigital.challenge_model.fakeCategorias
import com.amedigital.challenge_model.fakeProdutos
import com.amedigital.challenge_produto.CategoriaActivity
import com.amedigital.challenge_produto.ListaCategorias
import com.amedigital.challenge_produto.ListaProdutos
import com.amedigital.challenge_produto.ProdutoActivity
import com.amedigital.coreui.widgets.Banner

@Composable
fun Home() {
    val context = LocalContext.current
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
            CategoriaActivity.gotoCategoria(context, categoria)
        })
        ListaProdutos(fakeProdutos, onProdutoClick = { produto ->
            ProdutoActivity.gotoProduto(context, produto)
        })
    }
}