package com.github.czinkem.thevr_happyhour_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.czinkem.thevr_happyhour_app.presentation.App
import com.github.czinkem.thevr_happyhour_app.ui.theme.TheVRHappyHourAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheVRHappyHourAppTheme {
                App()
            }
        }
    }
}