package com.github.czinkem.thevr_happyhour_app.domain.usecase

import com.github.czinkem.thevr_happyhour_app.data.HappyHourStringSearchCache
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object CacheHappyHourForSearchUseCase: KoinComponent {
    private val cache  by inject<HappyHourStringSearchCache>()

    operator fun invoke(happyHourList: List<HappyHour>) {
        cache.cache(happyHourList)
    }
}