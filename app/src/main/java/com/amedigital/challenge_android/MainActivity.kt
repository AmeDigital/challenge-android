package com.amedigital.challenge_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amedigital.challenge_android.menu.MENUS
import com.amedigital.challenge_android.menu.Menu
import com.amedigital.challenge_android.menu.TopBar
import com.amedigital.challenge_home.Home
import com.amedigital.coreui.theme.ChallengeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        setContent {
            ChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScaffold()
                }
            }
        }
    }
}

@Composable
fun MainScaffold() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val toggleDrawer = {
        scope.launch {
            when (scaffoldState.drawerState.isOpen) {
                true -> scaffoldState.drawerState.close()
                false -> scaffoldState.drawerState.open()
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(onClickDrawerIcon = { toggleDrawer() }) },
        drawerContent = {
            Menu(
                MENUS,
                onMenuItemClicked = { menuItem ->
                    toggleDrawer()
                    navController.navigate(menuItem.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                Home()
            }
            composable("about") {
                About()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChallengeTheme {
        MainScaffold()
    }
}

@Composable
fun About() {
    Text(text = "Sobre o Lodjinha", style = MaterialTheme.typography.h4)
}