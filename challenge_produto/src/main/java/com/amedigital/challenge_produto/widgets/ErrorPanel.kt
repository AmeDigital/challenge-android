package com.amedigital.challenge_produto.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.amedigital.coreui.theme.ChallengeColors

@Composable
fun LogAndShowErrorPanel(error: Throwable) {

    Log.d("LODJINHA", error.toString())

    val scrollState = rememberScrollState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(ChallengeColors.GreyishBrown.copy(alpha = 0.8f))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp)
                .verticalScroll(scrollState)
                .background(Color.Red)
        ) {
            Text("FALHA", modifier = Modifier.padding(16.dp), color = Color.White)
            Divider()
            Text(error.message ?: "", color = Color.White, modifier = Modifier.padding(8.dp))
        }
    }
}