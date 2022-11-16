package com.amedigital.coreui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.amedigital.coreui.R


/*
* font: https://scene.zeplin.io/project/589b3ef2dba1a0801d3f1be1
*/

object ChallengeFonts {
    val pacifico = FontFamily(
        Font(R.font.pacifico_regular, FontWeight.Normal)
    )
    val robotoNormal = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal)
    )
    val robotoBold = FontFamily(
        Font(R.font.roboto_bold, FontWeight.Bold)
    )
}

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = (-0.3).sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)