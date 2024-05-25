package com.github.czinkem.thevr_happyhour_app.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen.HappyHourDetailScreen
import com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen.HappyHourDetailScreenWrapper
import com.github.czinkem.thevr_happyhour_app.presentation.mainScreen.MainScreen
import com.github.czinkem.thevr_happyhour_app.presentation.mainScreen.MainScreenWrapper

@Composable
fun App() {
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
                )
            }
            composable<HappyHourDetailScreen> {
                val screen = it.toRoute<HappyHourDetailScreen>()
                HappyHourDetailScreenWrapper(
                    modifier = Modifier.fillMaxSize(),
                    happyHour = screen.title,
                    navController = navController,
                )
            }
        }
    }
}

