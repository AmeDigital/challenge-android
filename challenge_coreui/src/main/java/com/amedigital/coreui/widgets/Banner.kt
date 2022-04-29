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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(images: List<String>, onImageClick: (image: String) -> Unit) {

    fun getAlpha(old: String, current: String): Float {
        return if (old == current) 1f else 0.6f
    }

    fun setCurrentImage(image: String, state: PagerState, scope: CoroutineScope){
        val page = images.indexOf(image)
        scope.launch {
            state.animateScrollToPage(page)
        }
    }

    if (images.isEmpty()) return

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.height(98.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(count = images.count(), state = pagerState) {
            AsyncImage(
                modifier = Modifier
                    .clickable { onImageClick(images[pagerState.currentPage]) },
                model = images[pagerState.currentPage],
                contentDescription = "Banner Image"
            )
        }
        Row(
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            images.forEach { image ->
                Card(
                    modifier = Modifier
                        .height(8.dp)
                        .width(8.dp)
                        .clickable { setCurrentImage(image, pagerState, coroutineScope) },
                    shape = CircleShape,
                    backgroundColor = Color.White.copy(
                        alpha = getAlpha(
                            image,
                            images[pagerState.currentPage]
                        )
                    )
                ) {}
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}