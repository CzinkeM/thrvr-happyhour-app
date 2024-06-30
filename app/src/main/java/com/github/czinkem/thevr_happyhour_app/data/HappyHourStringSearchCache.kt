package com.github.czinkem.thevr_happyhour_app.data

import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour

class HappyHourStringSearchCache {

    private val happyHourMap = mutableMapOf<Int, String>()

    fun cache(happyHours: List<HappyHour>) {
        happyHours.forEach {happyHour ->
            happyHourMap[happyHour.serialNumber] = happyHour.toDataString()
        }
    }

    fun search(searchedString: String): List<Int> {
        return happyHourMap.filter { it.value.contains(searchedString) }.keys.toList()
    }

    private fun HappyHour.toDataString(): String {
        return  this.serialNumber.toString() + " " + this.date + " " + this.title + this.chapters.map { chapter -> chapter.title }
    }
}