package com.amedigital.challenge_produto

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.amedigital.challenge_model.Produto
import com.amedigital.challenge_produto.widgets.ProdutoListItem
import com.amedigital.coreui.extensions.toMoneyBR
import com.amedigital.coreui.theme.ChallengeColors

@Composable
fun ListaProdutos(
    produtos: List<Produto>,
    onProdutoClick: (produto: Produto) -> Unit
) {
    Text(text = "Mais vendidos", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h6)
    produtos.forEach { produto ->
        Divider()
        ProdutoListItem(produto, onProdutoClick = { onProdutoClick(produto) })
    }
}