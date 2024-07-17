package com.github.czinkem.thevr_happyhour_app.domain.mapper

import com.github.czinkem.thevr_happyhour_app.data.dto.HappyHourDto
import com.github.czinkem.thevr_happyhour_app.data.dto.HappyHourVideoDto
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHourChapter
import com.github.czinkem.thevr_happyhour_app.domain.utils.HappyHourDateFormatter

fun HappyHourVideoDto.toHappyHour(): HappyHour {
    return HappyHour(
        title = this.title ?: throw Exception(),
        videoId = this.videoId  ?: throw Exception(),
        date = HappyHourDateFormatter.formatString(this.publishedDate  ?: throw Exception()),
        serialNumber = this.part  ?: throw Exception(),
        chapters = this.timeStampText?.toHappyHourChapterList()  ?: throw Exception()
    )
}


fun String.toHappyHourChapterList(): List<HappyHourChapter> {
    // FIXME: when there is not chapters (only "Nincs itt semmi") we cannot use the code below
//    val timeStamps = this.split("\\r\\n")
//    return timeStamps
//        .map { text -> with(text.split(" - ")) {
//            HappyHourChapter(this[0],this[1])
//        }
//    }
    return emptyList()
}

fun List<HappyHourVideoDto>.toHappyHourList(): List<HappyHour> {
    return this.map { it.toHappyHour() }
}
