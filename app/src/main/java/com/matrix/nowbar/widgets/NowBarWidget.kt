package com.matrix.nowbar.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
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
import com.matrix.nowbar.extensions.clamp
import com.matrix.nowbar.metrics.Dimensions
import com.matrix.nowbar.extensions.mapRange
import kotlinx.coroutines.launch
import kotlin.math.abs


@Composable
fun NowBarWidget(innerPadding: PaddingValues, widgets: List<@Composable () -> Unit>) {
    val scope = rememberCoroutineScope()
    val offsetY = remember { Animatable(0f) }
    val topIndex = remember { mutableIntStateOf(0) }

    val count = widgets.size

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onVerticalDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch { offsetY.snapTo(offsetY.value + dragAmount) }
                    },
                    onDragEnd = {
                        scope.launch {
                            if (offsetY.value < -50) {
                                offsetY.animateTo(-200f, tween(300))
                                offsetY.animateTo(0f, tween(100))
                                topIndex.intValue = (topIndex.intValue + 1) % count
                            } else if (offsetY.value > 50) {
                                offsetY.animateTo(200f, tween(300))
                                offsetY.animateTo(0f, tween(100))
                                topIndex.intValue = (topIndex.intValue - 1 + count) % count
                            } else offsetY.animateTo(0f, tween(200))
                        }
                    }
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        val reordered = (0 until count).map { (topIndex.intValue + it) % count }

        for (i in (count - 1) downTo 0) {
            val idx = reordered[i]
            val isTop = i == 0
            val isNext = i == 1

            key(idx) {
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            translationY =
                                (if (isTop) offsetY.value else 25f * i * 1.1f).clamp(-400f, 400f)
                        }
                        .fillMaxWidth(0.9f)
                        .height(80.dp)
                        .scale(
                            when {
                                isTop -> abs(offsetY.value).mapRange(200f, 0f, 0.9f, 1f)
                                isNext -> abs(offsetY.value).mapRange(
                                    0f,
                                    200f,
                                    1f - i * 0.12f,
                                    1.0f
                                )

                                else -> abs(offsetY.value).mapRange(
                                    0f,
                                    200f,
                                    1f - (i + 1) * 0.08f,
                                    1f - i * 0.12f
                                )
                            }
                        )
                        .shadow(
                            Dimensions.ShadowElevation,
                            RoundedCornerShape(Dimensions.BorderRadius),
                            ambientColor = Color.Black.copy(alpha = 0.8f)
                        )
                        .clip(RoundedCornerShape(Dimensions.BorderRadius))
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center,
                ) {
                    widgets[idx].invoke()
                }
            }
        }
    }
}
