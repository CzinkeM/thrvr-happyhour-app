package com.github.czinkem.thevr_happyhour_app.domain.mapper

import android.util.Log
import com.github.czinkem.thevr_happyhour_app.data.online.dto.HappyHourVideoDto
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHourChapter
import com.github.czinkem.thevr_happyhour_app.domain.utils.HappyHourDateFormatter
import com.github.czinkem.thevr_happyhour_app.domain.utils.YoutubeChapterUrlAssembler

fun HappyHourVideoDto.toHappyHour(): HappyHour {
    return HappyHour(
        title = this.title ?: throw Exception(),
        videoId = this.videoId  ?: throw Exception(),
        date = HappyHourDateFormatter.formatString(this.publishedDate  ?: throw Exception()),
        serialNumber = this.part  ?: throw Exception(),
        chapters = this.timeStampText?.toHappyHourChapterList(this.videoId!!)  ?: throw Exception()
    )
}
private const val TAG = "HappyHourMapper"

fun String.toHappyHourChapterList(videoId: String): List<HappyHourChapter> {
    // FIXME: when there is not chapters (only "Nincs itt semmi") we cannot use the code below
    try {
        if (this.contains("\r\n")) {
            val timeStamps = this.split("\r\n")
            return timeStamps
                .map { text -> text.split('-')}
                .map{ timeStampParts ->
                    HappyHourChapter(
                        title = timeStampParts[1],
                        timeStamp = timeStampParts[0],
                        uri = YoutubeChapterUrlAssembler.assemble(timestampString = timeStampParts[0], videoId = videoId)
                    )
                }
        }else if(this.contains("\n")) {
            val timeStamps = this.split("\n")
            return timeStamps
                .map { text -> text.split('-')}
                .map{ timeStampParts ->
                    HappyHourChapter(
                        title = timeStampParts[1],
                        timeStamp = timeStampParts[0],
                        uri = YoutubeChapterUrlAssembler.assemble(timestampString = timeStampParts[0], videoId = videoId)
                    )
                }
        }
        else {
            Log.d(TAG, "$this cannot splitted (nincs itt semmi)")
            return emptyList()
        }
    } catch (e: Exception) {
        Log.d(TAG, "$this cannot splitted ($e)")
        return emptyList()
    }
//    return emptyList()

}

fun List<HappyHourVideoDto>.toHappyHourList(): List<HappyHour> {
    return this.map { it.toHappyHour() }
}
