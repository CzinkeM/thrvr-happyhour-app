package com.github.czinkem.thevr_happyhour_app.domain.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object HappyHourDateFormatter {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")
    private val displayStringFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    fun formatString(dateString: String): LocalDate {
        return LocalDate.parse(dateString, dateFormatter)
    }

    fun formatLocalDate(date: LocalDate): String {
        return date.format(displayStringFormatter)
    }
}