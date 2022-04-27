package com.amedigital.challenge_android.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amedigital.challenge_android.R
import com.amedigital.coreui.theme.ChallengeFonts

@Composable
fun TopBar(currentMenuItem: MenuItem, onClickDrawerIcon: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onClickDrawerIcon() }) {
                Icon(painterResource(R.drawable.ic_left_menu), contentDescription = "Menu")
            }
        },
        title = {
            if (currentMenuItem.isHome) {
                Row() {
                    Image(
                        painter = painterResource(R.drawable.logo_navbar),
                        contentDescription = "Logo"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "a Lodjinha", fontFamily = ChallengeFonts.pacifico)
                }
            } else {
                Text(text = currentMenuItem.route)
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}