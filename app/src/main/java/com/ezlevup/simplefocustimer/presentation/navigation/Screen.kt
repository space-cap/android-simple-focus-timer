package com.ezlevup.simplefocustimer.presentation.navigation

sealed class Screen(val route: String) {
    data object Timer : Screen("timer")
    data object History : Screen("history")
}
