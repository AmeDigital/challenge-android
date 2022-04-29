package com.amedigital.coreui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amedigital.coreui.theme.ChallengeColors
import com.amedigital.coreui.theme.ChallengeFonts

@Composable
fun About(appName: String, author: String, description: String, image: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        image()
        Text(
            text = appName,
            style = MaterialTheme.typography.h3,
            fontFamily = ChallengeFonts.pacifico,
            color = ChallengeColors.GreyishBrown
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = author, fontWeight = FontWeight.Bold)
        Text(text = description, style = MaterialTheme.typography.caption)
    }
}