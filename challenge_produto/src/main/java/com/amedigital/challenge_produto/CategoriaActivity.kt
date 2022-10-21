package com.amedigital.challenge_produto

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_produto.ProdutoActivity.Companion.PARAM_PRODUTO
import com.amedigital.challenge_produto.widgets.ProdutoListItem
import com.amedigital.coreui.RouterManager
import com.amedigital.coreui.views.BaseActivity
import com.amedigital.coreui.widgets.TopBarNavigator
import com.amedigital.coreui.widgets.WaitingIndicator
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.ParametersHolder

class CategoriaActivity : BaseActivity() {

    companion object {
        const val HOST = "categoria"
        const val PARAM_CATEGORIA = "id"
        const val PARAM_CATEGORIA_DESCRIPTION = "description"
        const val NO_CATEGORIA = 0

        fun route(routerManager: RouterManager, context: Context, id: Int, description: String) {
            routerManager.route(
                context,
                HOST,
                mapOf(
                    Pair(PARAM_CATEGORIA, id.toString()),
                    Pair(PARAM_CATEGORIA_DESCRIPTION, description)
                )
            )
        }
    }

    private val categoriaId: Int
        get() {
            // make accessible from deeplink lodjinha://categoria?id=&description=
            // example local: adb shell am start -W -a android.intent.action.VIEW -d "lodjinha://categoria?id=1\&description=Games
            return intent.data?.getQueryParameter(PARAM_CATEGORIA)?.toInt() ?: NO_CATEGORIA
        }

    private val categoriaDescription: String
        get() {
            // make accessible from deeplink lodjinha://categoria?id=&description=
            // example local: adb shell am start -W -a android.intent.action.VIEW -d "lodjinha://categoria?id=1\&description=Games
            return intent.data?.getQueryParameter(PARAM_CATEGORIA_DESCRIPTION).toString()
        }

    private fun getParameters(): ParametersHolder {
        return ParametersHolder(mutableListOf(categoriaId))
    }

    private val viewModel: CategoriaViewModel by viewModel(parameters = { getParameters() })

    @Composable
    override fun activityContent() {

        val context = LocalContext.current
        val routerManager = get<RouterManager>()
        val produtosState = viewModel.produtos.observeAsState()

        when (val produtoValue = produtosState.value) {
            is Resource.Success -> listProdutos(produtoValue.value, onProdutoClick = { produto ->
                ProdutoActivity.route(routerManager, context, produto.id)
            })
            else -> WaitingIndicator()
        }
    }

    @Composable
    override fun topBar() {
        return TopBarNavigator(title = categoriaDescription) {
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
            if (produtos.isEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    text = "Ops...! Nenhum produto foi encontrado para a categoria ${categoriaDescription}. Tente a categoria Games, que sempre tem algo :-)."
                )
            }
        }
    }
}