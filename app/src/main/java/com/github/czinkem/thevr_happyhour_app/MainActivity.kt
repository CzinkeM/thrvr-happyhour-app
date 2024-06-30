package com.github.czinkem.thevr_happyhour_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.github.czinkem.thevr_happyhour_app.presentation.App
import com.github.czinkem.thevr_happyhour_app.ui.theme.TheVRHappyHourAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheVRHappyHourAppTheme {
                val sizeClass = calculateWindowSizeClass(activity = this)
                App(windowSizeClass = sizeClass)
            }
        }
    }
}