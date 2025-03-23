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
import com.matrix.nowbar.widgets.TimerWidget

class MainActivity : ComponentActivity() {

    val music = listOf(
        R.drawable.bliever to "Believer_Imagine Dragons",
        R.drawable.sit_next to "Sit Next To Me_Foster The People",
        R.drawable.dim_aster to "Across Dimming Asterisms_HOYO-MiX"
    )

    val widgets = listOf<@Composable () -> Unit>(
        { MediaPlayerWidget(music, PaddingValues.Absolute(0.dp)) },
        { TimerWidget() },
        { RoutinesWidget() },
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NowBarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NowBarWidget(innerPadding, widgets)
                }
            }
        }
    }
}


