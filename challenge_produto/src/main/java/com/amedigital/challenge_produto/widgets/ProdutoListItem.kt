package com.amedigital.challenge_produto.widgets

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
import com.amedigital.challenge_produto.R
import com.amedigital.coreui.extensions.toMoneyBR
import com.amedigital.coreui.theme.ChallengeColors

@Composable
fun ProdutoListItem(
    produto: Produto,
    onProdutoClick: (produto: Produto) -> Unit
) {
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
                    fallback = painterResource(com.amedigital.coreui.R.drawable.ic_broken_image),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column() {
                    Text(
                        modifier = Modifier
                            .width(constraints.maxWidth - 100.dp)
                            .padding(end = 8.dp),
                        text = produto.descricao, maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                    ) {
                        TextValorDe(produto.precoDe)
                        Spacer(modifier = Modifier.width(16.dp))
                        TextValorPor(produto.precoPor)
                    }
                }
            }
            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(R.drawable.disclosure_indicator),
                contentDescription = ""
            )
        }
    }
}