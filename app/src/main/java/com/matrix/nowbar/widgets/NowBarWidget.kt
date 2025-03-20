package com.matrix.nowbar.widgets

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.matrix.nowbar.metrics.Dimensions
import com.matrix.nowbar.extensions.clamp
import com.matrix.nowbar.extensions.sortWithTopValueAscending
import kotlinx.coroutines.launch
import kotlin.math.abs


@Composable
fun NowBarWidget(innerPadding: PaddingValues, count: Int = 1) {

    val scope = rememberCoroutineScope()
    var topIndex = remember { mutableIntStateOf(0) }
    val offsetY = remember { Animatable(0f) }

    val colors = mutableListOf<Color>(
        Color(0xFFFFC1D5),
        Color(0xFF15DE0E),
        Color(0xFFFF1000)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onVerticalDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch {
                            offsetY.snapTo(offsetY.value + dragAmount)
                        }
                    },
                    onDragEnd = {
                        scope.launch {
                            if (offsetY.value < -50) {
                                offsetY.animateTo(-200f, tween(300))
                                offsetY.animateTo(0f, tween(100))
                                topIndex.intValue = (topIndex.intValue + 1).clamp(0, count - 1)
                            } else if (offsetY.value > 50) {
                                offsetY.animateTo(200f, tween(300))
                                offsetY.animateTo(0f, tween(100))
                                topIndex.intValue = (topIndex.intValue - 1).clamp(0, count - 1)
                            } else offsetY.animateTo(0f, tween(200))
                        }
                    }
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        val nextItem = topIndex.intValue + 1;
        for (i in (count - 1) downTo 0) {
            val isTop = i == topIndex.intValue
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        translationY = if (isTop) offsetY.value else 10 + (2f - i)
                    }
                    .fillMaxWidth(0.9f)
                    .height(70.dp)
                    .scale(
                        if (isTop) 1f * abs(offsetY.value).clamp(1f, 0.9f)
                        else if (i == nextItem) 0.8f * abs(offsetY.value).clamp(1f, 1.1f)
                        else 1f - i * 0.12f
                    )
                    .shadow(
                        Dimensions.ShadowElevation,
                        RoundedCornerShape(Dimensions.BorderRadius),
                        ambientColor = Color.Black.copy(alpha = 0.4f)
                    )
                    .clip(RoundedCornerShape(Dimensions.BorderRadius))
                    .background(
                        colors
                            .sortWithTopValueAscending(topIndex.intValue)
                            .getOrNull(i)
                            ?: Color.Gray
                    )
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isTop) {
                    MediaPlayerWidget()
                } else {
                    Text("Widget $i", color = Color.White)
                }
            }
        }
    }
}