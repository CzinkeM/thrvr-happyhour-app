package com.github.czinkem.thevr_happyhour_app.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.czinkem.thevr_happyhour_app.data.IHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.data.offline.OfflineHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.data.online.OnlineHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.domain.mapper.toHappyHourList
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourChapterState
import com.github.czinkem.thevr_happyhour_app.domain.state.HappyHourState
import com.github.czinkem.thevr_happyhour_app.domain.state.SearchType
import com.github.czinkem.thevr_happyhour_app.domain.utils.HappyHourDateFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainScreenViewModel(
    private val repository: IHappyHourRepository
): ViewModel() {

    private val _happyHours = repository.happyHours()
        .map { happyHourVideoDtos ->
            happyHourVideoDtos.toHappyHourList().map { hh ->
                HappyHourState(
                    title =  hh.title,
                    videoId = hh.videoId,
                    date = HappyHourDateFormatter.formatLocalDate(hh.date),
                    serialNumber = hh.serialNumber,
                    chapters = hh.chapters.map { chapter ->
                        HappyHourChapterState(
                            title = chapter.title,
                            uri = chapter.uri,
                            timeStamp =  chapter.timeStamp
                        )
                    }
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val _displayedHappyHours = MutableStateFlow(listOf<HappyHourState>())
    val displayedHappyHours = _happyHours

    private val _happyHoursLoading = MutableStateFlow(false)
    val happyHoursLoading = _happyHoursLoading.asStateFlow()

    private val _animationCompleted = MutableStateFlow(false)

    private val _isHappyHoursFiltered = MutableStateFlow(false)
    val isHappyHoursFiltered = _isHappyHoursFiltered.asStateFlow()

    private val _lastSearchedValue = MutableStateFlow("")
    val lastSearchedValue = _lastSearchedValue.asStateFlow()

    fun loadHappyHours() {
        // TODO:  add loading animation
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadAllHappyHour()
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
            when(searchType) {
                SearchType.TEXT -> searchString(searchedValue)
                SearchType.NUMBER -> listOf(searchedValue.toInt())
                SearchType.DATE -> TODO()
            }
            _isHappyHoursFiltered.emit(true)
            if(!_animationCompleted.value) {
                while (!_animationCompleted.value) {
                    delay(100)
                }
            }
            _happyHoursLoading.emit(false)
        }
    }
    private fun searchString(searchedValue: String) {
        when(repository) {
            is OnlineHappyHourRepository -> {
                val dtos = repository.getHappyHourByFreeText(searchedValue)
                _displayedHappyHours.tryEmit(dtos.toHappyHourList().map { hh ->
                    HappyHourState(
                        title =  hh.title,
                        videoId = hh.videoId,
                        date = HappyHourDateFormatter.formatLocalDate(hh.date),
                        serialNumber = hh.serialNumber,
                        chapters = hh.chapters.map { chapter ->
                            HappyHourChapterState(
                                title = chapter.title,
                                uri = chapter.uri,
                                timeStamp =  chapter.timeStamp
                            )
                        }
                    )
                })
            }
            is OfflineHappyHourRepository -> {

            }
            else -> throw IllegalStateException()
        }
    }
}