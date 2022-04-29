package com.amedigital.coreui.widgets

import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amedigital.coreui.R

@Composable
fun TopBarNavigator(
    title: String,
    backgroundColor: Color? = null,
    onNavigator: () -> Unit
) {
    val elevation = if (title.isEmpty()) 0.dp else AppBarDefaults.TopAppBarElevation
    TopAppBar(
        elevation = elevation,
        navigationIcon = {
            IconButton(onClick = { onNavigator() }) {
                Icon(
                    painterResource(R.drawable.ic_back_arrow), contentDescription = ""
                )
            }
        },
        title = { Text(text = title) },
        backgroundColor = backgroundColor ?: MaterialTheme.colors.primaryVariant
    )
}