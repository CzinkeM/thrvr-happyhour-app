package com.github.czinkem.thevr_happyhour_app.data

import com.google.gson.Gson

class OfflineHappyHourRepository(private val cache: LocalDataCache): IHappyHourRepository {
    override fun getAll(): List<HappyHourDto> {
        val happyHours = Gson().fromJson(cache.json(), Array<HappyHourDto>::class.java )
        return happyHours.toList()
    }

    override fun getBySerialNumber(serialNumber: Int): HappyHourDto? {
        return Gson().fromJson(cache.json(), Array<HappyHourDto>::class.java)
            .firstOrNull { it.serialNumber.toInt() == serialNumber }
    }
}
