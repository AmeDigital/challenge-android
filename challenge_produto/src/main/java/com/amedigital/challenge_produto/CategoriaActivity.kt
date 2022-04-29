package com.amedigital.challenge_produto

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_model.fakeProdutos
import com.amedigital.challenge_produto.widgets.ProdutoListItem
import com.amedigital.coreui.views.BaseActivity
import com.amedigital.coreui.widgets.TopBarNavigator

class CategoriaActivity : BaseActivity() {

    companion object {
        const val PARAM_CATEGORIA = "categoria"

        fun gotoCategoria(context: Context, categoria: Categoria) {
            val intent = Intent(context, CategoriaActivity::class.java)
                .putExtra(PARAM_CATEGORIA, categoria)
            context.startActivity(intent)
        }
    }

    private val categoria: Categoria
        get() = intent.getParcelableExtra(PARAM_CATEGORIA)!!

    @Composable
    override fun activityContent() {
        val context = LocalContext.current
        listProdutos(fakeProdutos, onProdutoClick = { produto ->
            ProdutoActivity.gotoProduto(context, produto)
        })
    }

    @Composable
    override fun topBar() {
        return TopBarNavigator(title = categoria.descricao) {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    @Composable
    fun listProdutos(
        produtos: List<Produto>,
        onProdutoClick: (produto: Produto) -> Unit
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            var isFirst = true;
            produtos.forEach { produto ->
                if (!isFirst) {
                    Divider()
                }
                isFirst = false
                ProdutoListItem(produto, onProdutoClick = { onProdutoClick(produto) })
            }
        }
    }
}