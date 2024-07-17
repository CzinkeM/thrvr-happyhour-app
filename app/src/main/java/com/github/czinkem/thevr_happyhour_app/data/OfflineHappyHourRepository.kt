package com.github.czinkem.thevr_happyhour_app.data

import com.github.czinkem.thevr_happyhour_app.data.dto.HappyHourDto
import com.github.czinkem.thevr_happyhour_app.data.dto.HappyHourVideoDto
import com.google.gson.Gson
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
