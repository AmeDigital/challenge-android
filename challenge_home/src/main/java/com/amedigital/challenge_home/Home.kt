package com.amedigital.challenge_home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.amedigital.challenge_model.Banner
import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_model.api.Resource
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
    val bannerState = viewModel.banners.observeAsState()
    val categoriaState = viewModel.categorias.observeAsState()
    val maisVendidosState = viewModel.maisVendidos.observeAsState()

    when (val banner = bannerState.value) {
        is Resource.Requesting -> WaitingIndicator()
        is Resource.Failure -> LogAndShowErrorPanel(banner.throwable)
        is Resource.Success -> HomeView(banner.value, categoriaState.value, maisVendidosState.value)
    }
}

@Composable
private fun HomeView(
    images: List<Banner>,
    categorias: Resource<List<Categoria>>?,
    maisVendidos: Resource<List<Produto>>?
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Banner(images.map { it.linkUrl }, onImageClick = { image ->
            Log.d("HOME", image)
        })

        when (categorias) {
            is Resource.Requesting -> WaitingIndicator()
            is Resource.Failure -> LogAndShowErrorPanel(categorias.throwable)
            is Resource.Success -> CategoriasView(categorias.value)
        }

        when (maisVendidos) {
            is Resource.Requesting -> WaitingIndicator()
            is Resource.Failure -> LogAndShowErrorPanel(maisVendidos.throwable)
            is Resource.Success -> MaisVendidosView(maisVendidos.value)
        }
    }
}

@Composable
private fun CategoriasView(categorias: List<Categoria>) {
    val context = LocalContext.current
    ListaCategorias(categorias, onCategoriaClick = { categoria ->
        CategoriaActivity.gotoCategoria(context, categoria)
    })
}

@Composable
private fun MaisVendidosView(produtos: List<Produto>) {
    val context = LocalContext.current
    ListaProdutos(produtos, onProdutoClick = { produto ->
        ProdutoActivity.gotoProduto(context, produto)
    })
}