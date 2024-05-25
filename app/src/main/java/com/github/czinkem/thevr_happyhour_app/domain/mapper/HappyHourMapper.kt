package com.github.czinkem.thevr_happyhour_app.domain.mapper

import com.github.czinkem.thevr_happyhour_app.data.HappyHourChapterDto
import com.github.czinkem.thevr_happyhour_app.data.HappyHourDto
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHourChapter
import com.github.czinkem.thevr_happyhour_app.domain.utils.HappyHourDateFormatter

fun HappyHourDto.toHappyHour(): HappyHour {
    return HappyHour(
        title = this.title,
        url = this.url,
        date = HappyHourDateFormatter.format(this.date),
        serialNumber = this.serialNumber.toInt(),
        chapters = this.chapters.toHappyHourChapterList()
    )
}

fun HappyHourChapterDto.toHappyHourChapter(): HappyHourChapter {
    return HappyHourChapter(
        title = this.title,
        url = this.url
    )
}

fun List<HappyHourChapterDto>.toHappyHourChapterList(): List<HappyHourChapter> {
    return this.map { it.toHappyHourChapter() }
}

fun List<HappyHourDto>.toHappyHourList(): List<HappyHour> {
    return this.map { it.toHappyHour() }
}