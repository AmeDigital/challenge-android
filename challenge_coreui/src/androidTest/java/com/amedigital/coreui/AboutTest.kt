package com.amedigital.coreui

import androidx.compose.material.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amedigital.coreui.views.About
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutTest {

    @get:Rule
    var composeTestRule = createComposeRule()

    @Test
    fun SimpleShowAboutTest() {
        composeTestRule.setContent {
            About("Lodjinha","Alexnaldo Santos","abril/2022"){
                Text("image")
            }
        }
        composeTestRule.onNodeWithText("Lodjinha").assertIsDisplayed()
        composeTestRule.onNodeWithText("Alexnaldo Santos").assertIsDisplayed()
        composeTestRule.onNodeWithText("abril/2022").assertIsDisplayed()
        composeTestRule.onNodeWithText("image").assertIsDisplayed()
    }
}