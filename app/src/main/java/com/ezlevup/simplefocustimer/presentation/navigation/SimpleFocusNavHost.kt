package com.ezlevup.simplefocustimer.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ezlevup.simplefocustimer.presentation.timer.TimerScreen

@Composable
fun SimpleFocusNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Timer.route) {
        composable(Screen.Timer.route) {
            TimerScreen(onNavigateToHistory = { navController.navigate(Screen.History.route) })
        }
        composable(Screen.History.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("History Screen Placeholder")
            }
        }
    }
}
