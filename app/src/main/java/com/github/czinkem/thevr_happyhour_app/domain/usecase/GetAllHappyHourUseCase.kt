package com.github.czinkem.thevr_happyhour_app.domain.usecase

import com.github.czinkem.thevr_happyhour_app.data.IHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.domain.mapper.toHappyHourList
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object GetAllHappyHourUseCase: KoinComponent {
    private val repository  by inject<IHappyHourRepository>()
    operator fun invoke(): List<HappyHour> {
        return repository.getAll().toHappyHourList()
    }
}