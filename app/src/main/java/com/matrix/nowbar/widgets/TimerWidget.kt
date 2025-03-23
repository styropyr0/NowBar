package com.matrix.nowbar.widgets

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

@Composable
fun TimerWidget() {

    var playOrPauseButton = remember { mutableStateOf(R.drawable.ic_pause) }
    var time = remember { mutableIntStateOf(17) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF503164))
            .clip(RoundedCornerShape(Dimensions.BorderRadius)),
    ) {

        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(PaddingValues.Absolute(30.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(R.drawable.ic_timer_running),
                contentDescription = "Play/Pause",
                tint = if (playOrPauseButton.value == R.drawable.ic_pause) Color.White else Color(
                    0xFF6E85B3
                ),
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                "00:${time.intValue}",
                color = if (playOrPauseButton.value == R.drawable.ic_pause) Color.White else Color(
                    0xFF6E85B3
                ),
                fontSize = 28.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = {
                playOrPauseButton.value =
                    if (playOrPauseButton.value == R.drawable.ic_pause) R.drawable.ic_play
                    else R.drawable.ic_pause
            }) {
                Icon(
                    painterResource(playOrPauseButton.value),
                    contentDescription = "Start / Stop Timer",
                    tint = Color.White,
                    modifier = Modifier.size(25.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            IconButton(onClick = { time.intValue = 0 }) {
                Icon(
                    painterResource(R.drawable.ic_stop),
                    contentDescription = "Close Timer",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(30.dp))
        }
    }
}