package com.matrix.nowbar.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.matrix.nowbar.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.matrix.nowbar.metrics.Dimensions

@Composable
fun MediaPlayerWidget(music: List<Pair<Int, String>>, innerPadding: PaddingValues) {

    var playOrPauseButton = remember { mutableStateOf(R.drawable.ic_play) }
    var mediaIndex = remember { mutableIntStateOf(0) }
    var sharingColor = remember { mutableStateOf(Color.White) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clip(RoundedCornerShape(Dimensions.BorderRadius))
            .padding(innerPadding),
    ) {

        val image: Painter = painterResource(id = music[mediaIndex.intValue].first)

        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(8.dp)
                .zIndex(0f)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
                .zIndex(0.1f)
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(PaddingValues.Absolute(30.dp))
                .zIndex(0.2f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                mediaIndex.intValue =
                    if (mediaIndex.intValue == 0) music.size - 1
                    else mediaIndex.intValue - 1
            }, modifier = Modifier.size(35.dp)) {
                Icon(
                    painterResource(R.drawable.ic_prev),
                    contentDescription = "Previous",
                    tint = Color.White,
                    modifier = Modifier.size(25.dp)
                )
            }

            IconButton(onClick = {
                playOrPauseButton.value =
                    if (playOrPauseButton.value == R.drawable.ic_play) R.drawable.ic_pause
                    else R.drawable.ic_play
            }, modifier = Modifier.size(35.dp)) {
                Icon(
                    painterResource(playOrPauseButton.value),
                    contentDescription = "Play/Pause",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            IconButton(onClick = {
                mediaIndex.intValue =
                    if (mediaIndex.intValue >= music.size - 1) 0
                    else mediaIndex.intValue + 1
            }, modifier = Modifier.size(35.dp)) {
                Icon(
                    painterResource(R.drawable.ic_next),
                    contentDescription = "Next",
                    tint = Color.White,
                    modifier = Modifier.size(25.dp)
                )
            }

            Spacer(modifier = Modifier.size(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painterResource(R.drawable.ic_spotify),
                        contentDescription = "Spotify",
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(3.dp))

                    Text(
                        music[mediaIndex.intValue].second.split("_")[0],
                        fontSize = 16.sp,
                        color = Color.White,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.W700,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(1.dp))

                Text(
                    music[mediaIndex.intValue].second.split("_")[1],
                    fontSize = 10.sp,
                    color = Color.White,
                    lineHeight = 10.sp,
                    fontWeight = FontWeight.W400,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
            }

            IconButton(
                onClick = {
                    sharingColor.value =
                        if (sharingColor.value == Color.White) Color(0xFF43B3FF) else Color.White
                },
                modifier = Modifier.padding(PaddingValues.Absolute(right = 30.dp))
            ) {
                Icon(
                    painterResource(R.drawable.ic_shared_experiences),
                    contentDescription = "Share",
                    tint = sharingColor.value,
                    modifier = Modifier.size(25.dp)
                )
            }

        }
    }
}