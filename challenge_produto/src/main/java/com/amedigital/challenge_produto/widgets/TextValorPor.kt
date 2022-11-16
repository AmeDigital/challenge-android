package com.amedigital.challenge_produto.widgets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.amedigital.coreui.extensions.toMoneyBR
import com.amedigital.coreui.theme.ChallengeColors

@Composable
fun TextValorPor(valor: Double) {
    Text(
        text = "Por: ${valor.toMoneyBR()}",
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.body2,
        color = ChallengeColors.Tomato
    )
}