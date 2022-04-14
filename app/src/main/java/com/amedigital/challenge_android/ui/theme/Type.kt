package com.amedigital.challenge_android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amedigital.challenge_android.R


/*
* font: https://scene.zeplin.io/project/589b3ef2dba1a0801d3f1be1
*/

val pacificoFont = FontFamily(
    Font(R.font.pacifico_regular, FontWeight.Normal)
);

val robotoFontNormal = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal)
);

val robotoFontBold = FontFamily(
    Font(R.font.roboto_bold, FontWeight.Bold)
);

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