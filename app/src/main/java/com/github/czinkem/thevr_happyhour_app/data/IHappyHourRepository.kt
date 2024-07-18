package com.github.czinkem.thevr_happyhour_app.data

import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import kotlinx.coroutines.flow.Flow

interface IHappyHourRepository {

    fun happyHours(): Flow<List<HappyHour>>

    suspend fun loadAllHappyHour()

}