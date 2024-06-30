package com.github.czinkem.thevr_happyhour_app.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.czinkem.thevr_happyhour_app.presentation.mainScreen.MainScreen
import com.github.czinkem.thevr_happyhour_app.presentation.mainScreen.MainScreenWrapper

@Composable
fun App(windowSizeClass: WindowSizeClass) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = MainScreen,
        ) {

            composable<MainScreen> {
                MainScreenWrapper(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    windowSizeClass = windowSizeClass
                )
            }
        }
    }
}

