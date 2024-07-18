package com.github.czinkem.thevr_happyhour_app.domain.usecase

import com.github.czinkem.thevr_happyhour_app.data.offline.OfflineHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object GetAllHappyHourUseCase: KoinComponent {
    private val repository  by inject<OfflineHappyHourRepository>()
    operator fun invoke(): Flow<List<HappyHour>> {
        return repository.happyHours()
    }
}