package com.ezlevup.simplefocustimer.presentation.timer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun TimerScreen(onNavigateToHistory: () -> Unit, viewModel: TimerViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    val animatedProgress by
            animateFloatAsState(
                    targetValue = uiState.progress,
                    animationSpec = tween(durationMillis = 300),
                    label = "progress"
            )

    Box(
            modifier =
                    Modifier.fillMaxSize()
                            .background(
                                    Brush.verticalGradient(
                                            colors = listOf(Color(0xFF1A1A2E), Color(0xFF16213E))
                                    )
                            )
    ) {
        Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Section - Title
            Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                        text = "Focus Timer",
                        style =
                                MaterialTheme.typography.headlineMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                        text = if (uiState.isTimerRunning) "Stay focused!" else "Ready to focus?",
                        style =
                                MaterialTheme.typography.bodyMedium.copy(
                                        color = Color.White.copy(alpha = 0.7f)
                                )
                )
            }

            // Middle Section - Timer Circle
            Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f)) {
                Card(
                        modifier =
                                Modifier.size(320.dp)
                                        .shadow(
                                                elevation = 20.dp,
                                                shape = CircleShape,
                                                ambientColor = Color(0xFF6C63FF).copy(alpha = 0.3f),
                                                spotColor = Color(0xFF6C63FF).copy(alpha = 0.3f)
                                        ),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F3460))
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        // Background Circle
                        Canvas(modifier = Modifier.size(280.dp)) {
                            drawArc(
                                    color = Color.White.copy(alpha = 0.1f),
                                    startAngle = -90f,
                                    sweepAngle = 360f,
                                    useCenter = false,
                                    style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round),
                                    size = Size(size.width, size.height)
                            )
                        }

                        // Progress Circle with Gradient
                        Canvas(modifier = Modifier.size(280.dp)) {
                            val gradient =
                                    Brush.sweepGradient(
                                            colors =
                                                    listOf(
                                                            Color(0xFF6C63FF),
                                                            Color(0xFF5A52D5),
                                                            Color(0xFF6C63FF)
                                                    )
                                    )
                            drawArc(
                                    brush = gradient,
                                    startAngle = -90f,
                                    sweepAngle = 360f * animatedProgress,
                                    useCenter = false,
                                    style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round),
                                    size = Size(size.width, size.height)
                            )
                        }

                        // Time Display
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                    text = formatTime(uiState.currentTime),
                                    style =
                                            MaterialTheme.typography.displayLarge.copy(
                                                    fontSize = 64.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White
                                            )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                    text = "${(animatedProgress * 100).toInt()}%",
                                    style =
                                            MaterialTheme.typography.titleMedium.copy(
                                                    color = Color(0xFF6C63FF),
                                                    fontWeight = FontWeight.SemiBold
                                            )
                            )
                        }
                    }
                }
            }

            // Bottom Section - Controls
            Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
            ) {
                // Main Control Buttons
                Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                ) {
                    // Stop Button
                    FilledIconButton(
                            onClick = { viewModel.stopTimer() },
                            modifier = Modifier.size(64.dp),
                            colors =
                                    IconButtonDefaults.filledIconButtonColors(
                                            containerColor = Color(0xFFE94560).copy(alpha = 0.2f),
                                            contentColor = Color(0xFFE94560)
                                    ),
                            shape = CircleShape
                    ) {
                        Icon(
                                imageVector = Icons.Default.Stop,
                                contentDescription = "Stop",
                                modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    // Play/Pause Button (Larger)
                    FilledIconButton(
                            onClick = {
                                if (uiState.isTimerRunning) {
                                    viewModel.pauseTimer()
                                } else {
                                    viewModel.startTimer()
                                }
                            },
                            modifier =
                                    Modifier.size(80.dp)
                                            .shadow(
                                                    elevation = 12.dp,
                                                    shape = CircleShape,
                                                    ambientColor =
                                                            Color(0xFF6C63FF).copy(alpha = 0.5f)
                                            ),
                            colors =
                                    IconButtonDefaults.filledIconButtonColors(
                                            containerColor = Color(0xFF6C63FF),
                                            contentColor = Color.White
                                    ),
                            shape = CircleShape
                    ) {
                        Icon(
                                imageVector =
                                        if (uiState.isTimerRunning) Icons.Default.Pause
                                        else Icons.Default.PlayArrow,
                                contentDescription =
                                        if (uiState.isTimerRunning) "Pause" else "Start",
                                modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    // History Button
                    FilledIconButton(
                            onClick = onNavigateToHistory,
                            modifier = Modifier.size(64.dp),
                            colors =
                                    IconButtonDefaults.filledIconButtonColors(
                                            containerColor = Color.White.copy(alpha = 0.1f),
                                            contentColor = Color.White
                                    ),
                            shape = CircleShape
                    ) {
                        Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = "History",
                                modifier = Modifier.size(32.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Status Text
                Text(
                        text =
                                when {
                                    uiState.isTimerRunning -> "Timer is running..."
                                    uiState.currentTime < uiState.totalTime -> "Paused"
                                    else -> "Tap play to start"
                                },
                        style =
                                MaterialTheme.typography.bodyMedium.copy(
                                        color = Color.White.copy(alpha = 0.6f)
                                )
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

private fun formatTime(seconds: Long): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return "%02d:%02d".format(minutes, remainingSeconds)
}
