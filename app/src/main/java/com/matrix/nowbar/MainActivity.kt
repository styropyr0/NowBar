package com.matrix.nowbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matrix.nowbar.ui.theme.NowBarTheme
import com.matrix.nowbar.widgets.MediaPlayerWidget
import com.matrix.nowbar.widgets.NowBarWidget
import com.matrix.nowbar.widgets.RoutinesWidget
import com.matrix.nowbar.widgets.SportsWidget
import com.matrix.nowbar.widgets.TimerWidget

class MainActivity : ComponentActivity() {

    val music = listOf(
        R.drawable.bliever to "Believer_Imagine Dragons",
        R.drawable.sit_next to "Sit Next To Me_Foster The People",
        R.drawable.renegade to "Renegade_Axwell /\\ Ingrosso"
    )

    val widgets = listOf<@Composable () -> Unit>(
        { MediaPlayerWidget(music, PaddingValues.Absolute(0.dp)) },
        { TimerWidget(seconds = 65) },
        { RoutinesWidget(content = "At work and 2 others running") },
        { SportsWidget("ICC Champions Trophy 2025 (Final)\nIND vs NZ", "IND won by 4 wickets") },
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NowBarTheme(darkTheme = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NowBarWidget(innerPadding, widgets)
                }
            }
        }
    }
}


