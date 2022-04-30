package com.amedigital.challenge_produto.widgets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextDecoration
import com.amedigital.coreui.extensions.toMoneyBR
import com.amedigital.coreui.theme.ChallengeColors

@Composable
fun TextValorDe(valor: Double) {
    Text(
        text = "De: ${valor.toMoneyBR()}",
        style = MaterialTheme.typography.body1,
        color = ChallengeColors.GreyishBrown,
        textDecoration = TextDecoration.LineThrough
    )
}