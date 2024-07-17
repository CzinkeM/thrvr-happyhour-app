package com.github.czinkem.thevr_happyhour_app.domain.model

import java.time.LocalDate

data class HappyHour(
    val title: String,
    val videoId: String,
    val date: LocalDate,
    val serialNumber: Int,
    val chapters: List<HappyHourChapter>
)
