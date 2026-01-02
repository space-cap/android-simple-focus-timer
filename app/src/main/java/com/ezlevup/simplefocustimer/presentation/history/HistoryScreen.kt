package com.ezlevup.simplefocustimer.presentation.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ezlevup.simplefocustimer.domain.model.FocusSession
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(onNavigateBack: () -> Unit, viewModel: HistoryViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
            topBar = {
                TopAppBar(
                        title = { Text("Focus History") },
                        navigationIcon = {
                            IconButton(onClick = onNavigateBack) {
                                Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                )
                            }
                        }
                )
            }
    ) { paddingValues ->
        if (uiState.sessions.isEmpty()) {
            Column(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                        text = "No focus sessions yet",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
            ) { items(uiState.sessions) { session -> HistoryItem(session = session) } }
        }
    }
}

@Composable
private fun HistoryItem(session: FocusSession) {
    Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                        text = formatDate(session.startTime),
                        style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                        text = formatTime(session.startTime),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                    text = formatDuration(session.durationSeconds),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

private fun formatDate(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}

private fun formatTime(timestamp: Long): String {
    val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return timeFormat.format(Date(timestamp))
}

private fun formatDuration(seconds: Long): String {
    val minutes = seconds / 60
    return "${minutes}m"
}
