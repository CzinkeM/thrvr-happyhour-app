package com.github.czinkem.thevr_happyhour_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.github.czinkem.thevr_happyhour_app.presentation.App
import com.github.czinkem.thevr_happyhour_app.presentation.mainScreen.MainScreenViewModel
import com.github.czinkem.thevr_happyhour_app.ui.theme.TheVRHappyHourAppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel by inject<MainScreenViewModel> ()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.happyHoursLoading.value
            }
        }
        setContent {
            TheVRHappyHourAppTheme {
                val sizeClass = calculateWindowSizeClass(activity = this)
                App(windowSizeClass = sizeClass)
            }
        }
    }
}