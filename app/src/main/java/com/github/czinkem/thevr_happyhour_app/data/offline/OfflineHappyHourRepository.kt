package com.github.czinkem.thevr_happyhour_app.data.offline

import com.github.czinkem.thevr_happyhour_app.data.IHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.domain.mapper.toModelList
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineHappyHourRepository(
    private val dao: HappyHourDao
): IHappyHourRepository {

    override fun happyHours(): Flow<List<HappyHour>> {
        return dao.getHappyHour().map { it.toModelList() }
    }

    override suspend fun loadAllHappyHour() {
        TODO("Not yet implemented")
    }
}
