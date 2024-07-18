package com.github.czinkem.thevr_happyhour_app.domain.usecase

import com.github.czinkem.thevr_happyhour_app.data.offline.OfflineHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.data.online.OnlineHappyHourRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object SyncHappyHoursUseCase: KoinComponent {
    private val onlineHappyHourRepository by inject<OnlineHappyHourRepository>()
    private val offlineHappyHourRepository by inject<OfflineHappyHourRepository>()

    suspend fun invoke(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        withContext(dispatcher) {
            val targetPartNumber = offlineHappyHourRepository.lastPartNumber()
            val happyHoursToSync = if(targetPartNumber == null || targetPartNumber <= 0) {
                onlineHappyHourRepository.loadAllHappyHour()
            }else {
                onlineHappyHourRepository.loadHappyHoursUntilPartNumber(targetPartNumber)
            }
            offlineHappyHourRepository.saveHappyHoursToDb(happyHoursToSync)
        }
    }
}