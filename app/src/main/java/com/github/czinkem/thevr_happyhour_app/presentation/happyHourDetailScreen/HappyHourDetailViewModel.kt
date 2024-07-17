package com.github.czinkem.thevr_happyhour_app.presentation.happyHourDetailScreen

import androidx.lifecycle.ViewModel
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourChapterState
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import com.github.czinkem.thevr_happyhour_app.domain.usecase.GetHappyHourBySerialNumberUseCase
import com.github.czinkem.thevr_happyhour_app.domain.utils.HappyHourDateFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HappyHourDetailViewModel: ViewModel() {
//    fun getHappyHourBySerialNumber(serialNumber: Int): HappyHourState? {
//        val hh = GetHappyHourBySerialNumberUseCase(serialNumber)
//        return if(hh != null) {
//            HappyHourState(
//                title = hh.title,
//                url = hh.url,
//                date = HappyHourDateFormatter.formatLocalDate(hh.date),
//                serialNumber = hh.serialNumber,
//                chapters = hh.chapters.map { HappyHourChapterState(it.title, it.url) }
//            )
//        } else {
//            null
//        }
//    }

    private val _happyHours = MutableStateFlow(listOf<HappyHourState>())
    val happyHours = _happyHours.asStateFlow()

}