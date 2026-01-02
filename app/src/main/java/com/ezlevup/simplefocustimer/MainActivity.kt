package com.ezlevup.simplefocustimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ezlevup.simplefocustimer.presentation.navigation.SimpleFocusNavHost
import com.ezlevup.simplefocustimer.presentation.theme.SimpleFocusTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleFocusTimerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // innerPadding is not used here but could be passed if NavHost handled it,
                    // or we can just ignore it for now as SimpleFocusNavHost handles full screen.
                    // Ideally we should apply padding to the NavHost or pass it down.
                    // For now, let's just place SimpleFocusNavHost. Note that we might need to
                    // handle insets properly later.
                    Box(modifier = Modifier.padding(innerPadding)) { SimpleFocusNavHost() }
                }
            }
        }
    }
}
