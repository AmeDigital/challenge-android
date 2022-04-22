package com.amedigital.coreui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun Banner(images: List<String>, onImageClick: (image: String) -> Unit) {

    fun getAlpha(old: String, current: String): Float {
        return if (old == current) 1f else 0.6f
    }

    val (currentImage, setCurrentImage) = remember { mutableStateOf(images[0]) }

    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            modifier = Modifier.clickable { onImageClick(images[0]) },
            model = images[0],
            contentDescription = "Banner Image"
        )
        Row(
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            images.forEach { image ->
                Card(
                    modifier = Modifier
                        .height(8.dp)
                        .width(8.dp)
                        .clickable { setCurrentImage(image) },
                    shape = CircleShape,
                    backgroundColor = Color.White.copy(alpha = getAlpha(image, currentImage))
                ) {}
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}