package com.github.czinkem.thevr_happyhour_app.data.offline

import com.github.czinkem.thevr_happyhour_app.data.IHappyHourRepository
import com.github.czinkem.thevr_happyhour_app.data.LocalDataCache
import com.github.czinkem.thevr_happyhour_app.data.online.dto.HappyHourDto
import com.github.czinkem.thevr_happyhour_app.data.online.dto.HappyHourVideoDto
import kotlinx.coroutines.flow.StateFlow

class OfflineHappyHourRepository(private val cache: LocalDataCache): IHappyHourRepository {

    fun getAll(): List<HappyHourDto> {
        TODO()
//        val happyHours = Gson().fromJson(cache.json(), Array<HappyHourDto>::class.java )
//        return happyHours.toList()
    }

    fun getBySerialNumber(serialNumber: Int): HappyHourDto? {
        TODO()
//        return null
//        return Gson().fromJson(cache.json(), Array<HappyHourDto>::class.java)
//            .firstOrNull { it.serialNumber.toInt() == serialNumber }
    }

    override fun happyHours(): StateFlow<List<HappyHourVideoDto>> {
        TODO("Not yet implemented")
    }

    override fun loadAllHappyHour() {
        TODO("Not yet implemented")
    }
}
