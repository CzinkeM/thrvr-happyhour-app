package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourChapterState
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import com.github.czinkem.thevr_happyhour_app.domain.usecase.GetAllHappyHourUseCase
import com.github.czinkem.thevr_happyhour_app.domain.utils.HappyHourDateFormatter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainScreenViewModel: ViewModel() {

    private val _happyHours = MutableStateFlow(listOf<HappyHourState>())
    val happyHours = _happyHours.asStateFlow()

    private val _happyHoursLoading = MutableStateFlow(true)
    val happyHoursLoading = _happyHoursLoading.asStateFlow()

    private val _animationCompleted = MutableStateFlow(false)


    fun initHappyHours() {
        _happyHoursLoading.update { true }
        measureTimeMillis {
            _happyHours.update {
                GetAllHappyHourUseCase
                    .invoke()
                    .map {
                        HappyHourState(
                            title =  it.title,
                            url = it.url,
                            date = HappyHourDateFormatter.formatLocalDate(it.date),
                            serialNumber = it.serialNumber,
                            chapters = it.chapters.map { HappyHourChapterState(it.title,it.url) }
                        )
                    }
            }
        }
        viewModelScope.launch {
            if(!_animationCompleted.value) {
                while (!_animationCompleted.value) {
                    delay(100)
                }
            }
            _happyHoursLoading.emit(false)
        }
    }

    fun onAnimationProgressChange(isCompleted: Boolean) {
        _animationCompleted.update { isCompleted }
    }
}