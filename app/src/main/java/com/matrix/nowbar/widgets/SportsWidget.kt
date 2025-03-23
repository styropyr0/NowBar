package com.matrix.nowbar.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.matrix.nowbar.R
import com.matrix.nowbar.metrics.Dimensions

@Composable
fun SportsWidget(title: String, content: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x48C1C1F3))
            .clip(RoundedCornerShape(Dimensions.BorderRadius)),
    ) {

        Image(
            painter = painterResource(R.drawable.sports),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(40.dp)
                .zIndex(0f)
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(PaddingValues.Absolute(left = 30.dp, right = 30.dp))
                .zIndex(0.2f),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Icon(
                painterResource(R.drawable.ic_india),
                contentDescription = "Team L",
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified
            )


            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f)) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        title.split("\n")[0],
                        color = Color(0xFFB7B7B7),
                        fontSize = 10.sp,
                        lineHeight = 10.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.padding(vertical = 2.dp))

                    Text(
                        title.split("\n")[1],
                        color = Color(0xFFFFFFFF),
                        fontSize = 13.sp,
                        lineHeight = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.padding(vertical = 1.dp))

                    Text(
                        content,
                        color = Color(0xFFFA945B),
                        fontSize = 15.sp,
                        lineHeight = 15.sp,
                        fontWeight = FontWeight.W700
                    )
                }
            }

            Icon(
                painterResource(R.drawable.ic_nz),
                contentDescription = "Team R",
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified
            )

        }
    }
}