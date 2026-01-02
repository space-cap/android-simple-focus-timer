package com.ezlevup.simplefocustimer.presentation.timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun TimerScreen(onNavigateToHistory: () -> Unit, viewModel: TimerViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                    progress = { uiState.progress },
                    modifier = Modifier.size(250.dp),
                    strokeWidth = 10.dp,
            )
            Text(
                    text = formatTime(uiState.currentTime),
                    style = MaterialTheme.typography.displayLarge
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            if (uiState.isTimerRunning) {
                Button(onClick = { viewModel.pauseTimer() }) { Text("Pause") }
            } else {
                Button(onClick = { viewModel.startTimer() }) {
                    Text(if (uiState.currentTime < uiState.totalTime) "Resume" else "Start")
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { viewModel.stopTimer() }) { Text("Stop") }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onNavigateToHistory) { Text("View History") }
    }
}

private fun formatTime(seconds: Long): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return "%02d:%02d".format(minutes, remainingSeconds)
}
