package com.amedigital.challenge_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amedigital.challenge_android.menu.MENUS
import com.amedigital.challenge_android.menu.Menu
import com.amedigital.challenge_android.menu.TopBar
import com.amedigital.challenge_home.Home
import com.amedigital.coreui.theme.ChallengeTheme
import com.amedigital.coreui.views.About
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val (currentMenuItem, setCurrentMenuItem) = remember { mutableStateOf(MENUS.first()) }
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
        topBar = { TopBar(currentMenuItem, onClickDrawerIcon = { toggleDrawer() }) },
        drawerContent = {
            Menu(
                MENUS,
                onMenuItemClicked = { menuItem ->
                    setCurrentMenuItem(menuItem)
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
            composable("sobre") {
                AboutLodjinha()
            }
        }
    }
}

@Composable
fun AboutLodjinha() {
    About(
        appName = "a Lodjinha",
        author = "Alexnaldo Santos",
        description = "27 de abril de 2022"
    ) {
        Image(
            painter = painterResource(R.drawable.logo_sobre),
            contentDescription = "Logo"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChallengeTheme {
        MainScaffold()
    }
}