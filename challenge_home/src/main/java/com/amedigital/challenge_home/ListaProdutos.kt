package com.amedigital.challenge_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.Produto

@Composable
fun ListaProdutos(
    produtos: List<Produto>,
    onProdutoClick: (produto: Produto) -> Unit
) {
    Text(text = "Mais vendidos", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h6)
    produtos.forEach { produto ->
        Divider()
        BoxWithConstraints(
            contentAlignment = Alignment.CenterEnd
        ) {
            val constraints = this
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onProdutoClick(produto) }
        ) {
            Row(
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(64.dp),
                    model = produto.urlImagem,
                    contentDescription = produto.descricao,
                    fallback = painterResource(R.drawable.no_image),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.width(constraints.maxWidth - 100.dp),
                    text = produto.descricao, maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(R.drawable.disclosure_indicator),
                contentDescription = ""
            )
        }
    }
    }
}