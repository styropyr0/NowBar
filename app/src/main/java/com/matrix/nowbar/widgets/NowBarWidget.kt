package com.matrix.nowbar.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun NowBarWidget(innerPadding: PaddingValues, count: Int = 1) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center,
    ) {
        for (i in 1..<count) {
            if (i > 1) break
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .offset(y = (8 - i * 5).dp)
                    .fillMaxWidth(0.9f)
                    .height(70.dp)
                    .shadow(
                        12.dp,
                        RoundedCornerShape(20.dp),
                        ambientColor = Color.Black.copy(alpha = 0.4f)
                    )
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(0xFF15DE0E))
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .offset(y = 8.dp)
                .height(70.dp)
                .shadow(
                    4.dp,
                    RoundedCornerShape(20.dp),
                    ambientColor = Color.Black.copy(alpha = 0.2f)
                )
                .clip(RoundedCornerShape(30.dp))
                .background(Color(0xFFFFC1D5))
        ) {
            MediaPlayerWidget()
        }
    }
}