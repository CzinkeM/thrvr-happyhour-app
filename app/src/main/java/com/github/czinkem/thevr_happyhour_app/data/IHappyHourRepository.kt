package com.github.czinkem.thevr_happyhour_app.data

import com.github.czinkem.thevr_happyhour_app.data.dto.HappyHourVideoDto
import kotlinx.coroutines.flow.StateFlow

interface IHappyHourRepository {

    fun happyHours(): StateFlow<List<HappyHourVideoDto>>

    fun loadAllHappyHour()

}