package com.github.czinkem.thevr_happyhour_app.domain.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object HappyHourDateFormatter {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    fun format(dateString: String): LocalDate {
        return LocalDate.parse(dateString, dateFormatter)
    }
}