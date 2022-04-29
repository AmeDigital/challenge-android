package com.amedigital.challenge_home

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.amedigital.challenge_model.Banner
import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.fakeBanners
import com.amedigital.challenge_model.fakeCategorias
import com.amedigital.challenge_model.fakeProdutos
import com.amedigital.challenge_produto.CategoriaActivity
import com.amedigital.challenge_produto.ListaCategorias
import com.amedigital.challenge_produto.ListaProdutos
import com.amedigital.challenge_produto.ProdutoActivity
import com.amedigital.challenge_produto.widgets.LogAndShowErrorPanel
import com.amedigital.coreui.widgets.Banner
import com.amedigital.coreui.widgets.WaitingIndicator
import org.koin.androidx.compose.getViewModel

@Composable
fun Home() {
    val viewModel = getViewModel<HomeViewModel>()
    val bannerState = viewModel.banner.observeAsState()

    when (val banner = bannerState.value) {
        is Resource.Requesting -> WaitingIndicator()
        is Resource.Failure -> LogAndShowErrorPanel(banner.throwable)
        is Resource.Success -> HomeView(banner.value)
    }
}

@Composable
private fun HomeView(images: List<Banner>) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Banner(images.map { it.linkUrl }, onImageClick = { image ->
            Log.d("HOME", image)
        })
        ListaCategorias(fakeCategorias, onCategoriaClick = { categoria ->
            CategoriaActivity.gotoCategoria(context, categoria)
        })
        ListaProdutos(fakeProdutos, onProdutoClick = { produto ->
            ProdutoActivity.gotoProduto(context, produto)
        })
    }
}