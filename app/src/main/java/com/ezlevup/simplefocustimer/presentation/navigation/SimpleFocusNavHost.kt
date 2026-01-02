package com.ezlevup.simplefocustimer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ezlevup.simplefocustimer.presentation.history.HistoryScreen
import com.ezlevup.simplefocustimer.presentation.timer.TimerScreen

@Composable
fun SimpleFocusNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Timer.route) {
        composable(Screen.Timer.route) {
            TimerScreen(onNavigateToHistory = { navController.navigate(Screen.History.route) })
        }
        composable(Screen.History.route) {
            HistoryScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}
