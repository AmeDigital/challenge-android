package com.amedigital.challenge_android.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.amedigital.challenge_android.R
import com.amedigital.coreui.theme.ChallengeColors
import com.amedigital.coreui.theme.ChallengeFonts
import com.amedigital.coreui.theme.ChallengeTheme

internal class MenuItem(val title: String, val route: String, val imageResourceId: Int) {
}

internal val MENUS = listOf(
    MenuItem("Home", "home", R.drawable.home_menu),
    MenuItem(
        "Sobre o aplicativo",
        "about",
        R.drawable.tag_menu
    )
)

@Composable
internal fun Menu(
    menus: List<MenuItem>,
    modifier: Modifier = Modifier,
    onMenuItemClicked: (menuItem: MenuItem) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.menu_pattern),
                contentDescription = ""
            )
            Card(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                shape = CircleShape,
                backgroundColor = ChallengeColors.Tomato
            ) {
                Image(
                    modifier = Modifier
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.logo_menu),
                    contentDescription = ""
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 16.dp),
                text = "a Lodjinha",
                fontFamily = ChallengeFonts.pacifico,
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
        }
        menus.forEach { menu ->
            Row(
                modifier = Modifier.padding(start = 16.dp, top = 32.dp)
            ) {
                Image(
                    painter = painterResource(menu.imageResourceId),
                    contentDescription = menu.title
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = menu.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onMenuItemClicked(menu)
                    }
                )
            }
        }
    }
}

@Preview(backgroundColor = 0xFFD5D5D5)
@Composable
fun DrawerPreview() {
    ChallengeTheme() {
        Menu(
            MENUS
        ){}
    }
}