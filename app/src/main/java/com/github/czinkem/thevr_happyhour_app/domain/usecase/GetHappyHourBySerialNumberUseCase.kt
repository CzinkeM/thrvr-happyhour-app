package com.github.czinkem.thevr_happyhour_app.domain.usecase

import com.github.czinkem.thevr_happyhour_app.data.IHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.di.AppKoinModule
import com.github.czinkem.thevr_happyhour_app.domain.mapper.toHappyHour
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

object GetHappyHourBySerialNumberUseCase: KoinComponent {
    private val repository  by inject<IHappyHourRepository>(named(AppKoinModule.REPOSITORY_OFFLINE_NAME))
    operator fun invoke(serialNumber: Int): HappyHour? {
        return repository.getBySerialNumber(serialNumber)?.toHappyHour()
    }
}