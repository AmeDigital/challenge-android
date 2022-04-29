package com.amedigital.challenge_produto

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun ListaCategorias(
    categorias: List<Categoria>,
    onCategoriaClick: (categoria: Categoria) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = "Categorias", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h6)
        Divider()
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
        ) {
            categorias.forEach { categoria ->
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .size(116.dp)
                        .padding(8.dp)
                        .clickable { onCategoriaClick(categoria) }
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(64.dp),
                        model = categoria.urlImagem,
                        contentDescription = categoria.descricao,
                        fallback = painterResource(com.amedigital.coreui.R.drawable.ic_broken_image),
                    )
                    Text(
                        text = categoria.descricao, fontWeight = FontWeight.Bold, maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Divider()
    }
}