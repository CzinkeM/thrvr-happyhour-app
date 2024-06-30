package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.czinkem.thevr_happyhour_app.data.HappyHourStringSearchCache
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourChapterState
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import com.github.czinkem.thevr_happyhour_app.domain.state.SearchType
import com.github.czinkem.thevr_happyhour_app.domain.usecase.GetAllHappyHourUseCase
import com.github.czinkem.thevr_happyhour_app.domain.utils.HappyHourDateFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainScreenViewModel(
    private val stringSearchCache: HappyHourStringSearchCache
): ViewModel() {

    private val _happyHours = MutableStateFlow(listOf<HappyHourState>())

    private val _displayedHappyHours = MutableStateFlow(listOf<HappyHourState>())
    val displayedHappyHours = _displayedHappyHours.asStateFlow()

    private val _happyHoursLoading = MutableStateFlow(true)
    val happyHoursLoading = _happyHoursLoading.asStateFlow()

    private val _animationCompleted = MutableStateFlow(false)

    private val _isHappyHoursFiltered = MutableStateFlow(false)
    val isHappyHoursFiltered = _isHappyHoursFiltered.asStateFlow()

    private val _lastSearchedValue = MutableStateFlow("")
    val lastSearchedValue = _lastSearchedValue.asStateFlow()

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
                            chapters = it.chapters.map { chapter ->
                                HappyHourChapterState(chapter.title,chapter.url)
                            }
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

    fun showAllHappyHour() {
        _isHappyHoursFiltered.update { false }
        _displayedHappyHours.update { _happyHours.value }
    }

    fun onAnimationProgressChange(isCompleted: Boolean) {
        _animationCompleted.update { isCompleted }
    }

    fun search(searchType: SearchType, searchedValue: String) {
        viewModelScope.launch(Dispatchers.Default) {
            _lastSearchedValue.emit(searchedValue)
            _animationCompleted.emit(false)
            _happyHoursLoading.emit(true)
            val resultSerialNumber = when(searchType) {
                SearchType.TEXT -> searchString(searchedValue)
                SearchType.NUMBER -> listOf(searchedValue.toInt())
                SearchType.DATE -> TODO()
            }
            val hhs = _happyHours.value.filter { happyHour -> happyHour.serialNumber in resultSerialNumber }
            _displayedHappyHours.emit(hhs)
            _isHappyHoursFiltered.emit(true)
            if(!_animationCompleted.value) {
                while (!_animationCompleted.value) {
                    delay(100)
                }
            }
            _happyHoursLoading.emit(false)
        }
    }
    private fun searchString(searchedValue: String): List<Int> {
        return stringSearchCache.search(searchedValue)
    }
}