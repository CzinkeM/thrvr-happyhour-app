package com.github.czinkem.thevr_happyhour_app.data

data class HappyHourDto(
    val title: String,
    val url: String,
    val date: String,
    val serialNumber: String,
    val chapters: List<HappyHourChapterDto>
)
