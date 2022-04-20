package com.amedigital.challenge_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amedigital.challenge_android.menu.MENUS
import com.amedigital.challenge_android.menu.Menu
import com.amedigital.challenge_android.menu.TopBar
import com.amedigital.challenge_home.HomeActivity
import com.amedigital.coreui.theme.ChallengeandroidTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureScreen()
    }

    private fun configureScreen() {
        setContent {
            ChallengeandroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    buildMenu()
                }
            }
        }
    }
}

@Composable
fun buildMenu() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val toggleDrawer = {
        scope.launch {
            when (drawerState.isOpen) {
                true -> drawerState.close()
                false -> drawerState.open()
            }
        }
    }
    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
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
            activity("about") {
                activityClass = HomeActivity::class
            }
            composable("home") {
                Help(
                    openDrawer = {
                        toggleDrawer()
                    }
                )
            }
//            composable("about") {
//                Account(
//                    openDrawer = {
//                        toggleDrawer()
//                    }
//                )
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChallengeandroidTheme {
        buildMenu()
    }
}

@Composable
fun Account(openDrawer: () -> Unit) {
    Scaffold(
        topBar = { TopBar { openDrawer() } }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Account.", style = MaterialTheme.typography.h4)
        }
    }
}

@Composable
fun Help(openDrawer: () -> Unit) {
    Scaffold(
        topBar = { TopBar { openDrawer() } }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Help.", style = MaterialTheme.typography.h4)
        }
    }
}