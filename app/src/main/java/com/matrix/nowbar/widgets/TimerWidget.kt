package com.matrix.nowbar.widgets

import androidx.compose.animation.animateColorAsState
import com.matrix.nowbar.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matrix.nowbar.metrics.Dimensions
import java.lang.String.format
import kotlin.math.abs

@Composable
fun TimerWidget(seconds: Int) {

    var playOrPauseButton = remember { mutableStateOf(R.drawable.ic_pause) }
    var time = remember { mutableIntStateOf(seconds) }
    var isRunning = remember { mutableStateOf(true) }

    androidx.compose.runtime.LaunchedEffect(isRunning.value) {
        while (isRunning.value) {
            kotlinx.coroutines.delay(1000L)
            time.intValue -= 1
        }
    }

    val animatedColor = animateColorAsState(
        targetValue = if (isRunning.value && time.intValue >= 0) Color.White
        else if (time.intValue < 0) Color(0xFFFF4800)
        else Color(0xFFAF88DA),
        label = "Timer Color Transition"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF503164))
            .clip(RoundedCornerShape(Dimensions.BorderRadius)),
    ) {
        playOrPauseButton.value = if (isRunning.value) R.drawable.ic_pause else R.drawable.ic_play
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(PaddingValues.Absolute(30.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(R.drawable.ic_timer_running),
                contentDescription = "Play/Pause",
                tint = animatedColor.value,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                format(
                    "${if (time.intValue < 0) "-" else ""}%02d:%02d",
                    abs(time.intValue / 60),
                    abs(time.intValue % 60)
                ),
                color = animatedColor.value,
                fontSize = 28.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.weight(1f)
            )

            if (time.intValue >= 0)
                IconButton(onClick = {
                    isRunning.value = !isRunning.value
                }) {
                    Icon(
                        painterResource(playOrPauseButton.value),
                        contentDescription = "Start / Stop Timer",
                        tint = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                }

            Spacer(modifier = Modifier.width(10.dp))

            IconButton(onClick = {
                time.intValue = seconds
                isRunning.value = false
            }) {
                Icon(
                    painterResource(R.drawable.ic_stop),
                    contentDescription = "Close Timer",
                    tint = Color.Red,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(30.dp))
        }
    }
}