package com.amedigital.coreui.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amedigital.coreui.theme.ChallengeTheme

abstract class BaseActivity : ComponentActivity() {
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
                    scaffold()
                }
            }
        }
    }

    @Composable
    abstract fun activityContent()

    @Composable
    abstract fun topBar()

    @Composable
    open fun floatingActionButton() {
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun scaffold() {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { topBar() },
            floatingActionButton = { floatingActionButton() }
        )
        {
            activityContent()
        }
    }
}