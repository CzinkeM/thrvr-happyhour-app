package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.lifecycle.ViewModel
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel: ViewModel() {

    private val _happyHours = MutableStateFlow(listOf<HappyHourState>())
    val happyHours = _happyHours.asStateFlow()

}