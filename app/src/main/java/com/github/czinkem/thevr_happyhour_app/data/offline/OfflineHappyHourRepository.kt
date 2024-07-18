package com.github.czinkem.thevr_happyhour_app.data.offline

import com.github.czinkem.thevr_happyhour_app.data.online.dto.HappyHourVideoDto
import com.github.czinkem.thevr_happyhour_app.domain.mapper.toDatabaseEntityList
import com.github.czinkem.thevr_happyhour_app.domain.mapper.toModelList
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineHappyHourRepository(
    private val dao: HappyHourDao
) {
    suspend fun lastPartNumber() = dao.getLastPartNumberOrNull()

    fun happyHours(): Flow<List<HappyHour>> {
        return dao.getHappyHours().map { it.toModelList() }
    }

    suspend fun saveHappyHoursToDb(happyHours: List<HappyHourVideoDto>) {
        dao.upsertHappyHour(*happyHours.toDatabaseEntityList().toTypedArray())
    }

    suspend fun clear() {
        dao.deleteAll()
    }
}
