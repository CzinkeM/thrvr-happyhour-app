package com.github.czinkem.thevr_happyhour_app.data

import com.github.czinkem.thevr_happyhour_app.data.online.dto.HappyHourVideoDto

class HappyHourVideoSearchCache: SearchCache<HappyHourVideoDto> {

    private val happyHourMap = mutableMapOf<Int, String>()

    override fun cache(happyHours: List<HappyHourVideoDto>) {
        happyHours.forEach {happyHour ->
            happyHourMap[happyHour.id!!] = happyHour.toDataString()
        }
    }

    override fun search(searchedString: String): List<Int> {
        return happyHourMap.filter { it.value.contains(searchedString) }.keys.toList()
    }

    private fun HappyHourVideoDto.toDataString(): String {
        return  this.part.toString() + " " + this.publishedDate + " " + this.title + this.timeStampText
    }
}