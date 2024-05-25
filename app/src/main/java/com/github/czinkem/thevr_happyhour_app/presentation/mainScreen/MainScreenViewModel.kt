package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.lifecycle.ViewModel
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourChapterState
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import com.github.czinkem.thevr_happyhour_app.domain.usecase.GetAllHappyHourUseCase
import com.github.czinkem.thevr_happyhour_app.domain.utils.HappyHourDateFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel: ViewModel() {

    private val _happyHours = MutableStateFlow(listOf<HappyHourState>())
    val happyHours = _happyHours.asStateFlow()

    fun initHappyHours() {
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

}