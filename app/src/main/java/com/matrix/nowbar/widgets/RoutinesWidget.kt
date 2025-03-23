package com.matrix.nowbar.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.matrix.nowbar.R
import com.matrix.nowbar.metrics.Dimensions

@Composable
fun RoutinesWidget() {
    var playOrPauseButton = remember { mutableStateOf(R.drawable.ic_pause) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF303164))
            .clip(RoundedCornerShape(Dimensions.BorderRadius)),
    ) {

        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(PaddingValues.Absolute(30.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painterResource(R.drawable.ic_routines),
                contentDescription = "Play/Pause",
                tint = if (playOrPauseButton.value == R.drawable.ic_pause) Color.White else Color(
                    0xFF6E85B3
                ),
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Column {

                Text(
                    "Bixby Routines",
                    color = Color(0xFF6E85B3),
                    fontSize = 11.sp,
                    lineHeight = 11.sp,
                    fontWeight = FontWeight.W400
                )

                Text(
                    "At work and 2 others running",
                    color = if (playOrPauseButton.value == R.drawable.ic_pause) Color.White else Color(
                        0xFF6E85B3
                    ),
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.W700
                )
            }
        }
    }
}