package com.github.czinkem.thevr_happyhour_app.domain.mapper

import android.util.Log
import com.github.czinkem.thevr_happyhour_app.data.offline.entity.HappyHourEntity
import com.github.czinkem.thevr_happyhour_app.data.online.dto.HappyHourVideoDto
import com.github.czinkem.thevr_happyhour_app.domain.exception.MappingException
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHour
import com.github.czinkem.thevr_happyhour_app.domain.model.HappyHourChapter
import com.github.czinkem.thevr_happyhour_app.domain.utils.HappyHourDateFormatter
import com.github.czinkem.thevr_happyhour_app.domain.utils.YoutubeChapterUrlAssembler

fun HappyHourVideoDto.toHappyHour(): HappyHour {
    return HappyHour(
        title = this.title ?: throw Exception(),
        videoId = this.videoId  ?: throw Exception(),
        date = HappyHourDateFormatter.formatString(this.publishedDate  ?: throw Exception()),
        part = this.part  ?: throw Exception(),
        chapters = this.timeStampText?.toHappyHourChapterList(this.videoId!!)  ?: throw Exception()
    )
}
private const val TAG = "HappyHourMapper"
private fun String.toHappyHourChapterList(videoId: String): List<HappyHourChapter> {
    val hhChapters = mutableListOf<HappyHourChapter>()
    if(this.contains('\n')) {
        val chapters = this.split('\n')
        chapters.forEach {
            val chapterString = it.removeSuffix("\r")
            val chapterParts = chapterString.split("-")
            when {
                chapterParts.size == 1 -> {
                    if (chapterParts[0].isBlank()) {
                        return@forEach
                    }
                    val title = chapterParts[0]
                    val timestamp = "00:00:00"
                    hhChapters.add(
                        HappyHourChapter(
                            title = title.trim(),
                            timeStamp = timestamp,
                            uri = YoutubeChapterUrlAssembler.assemble(timestampString = timestamp, videoId = videoId)
                        )
                    )
                }
                chapterParts.size == 2 -> {
                    // TODO: sometime there is a chapter where the string with 0 index isn't a time code string pl időjárás-jelentés, currently in these cases we return 0
                    hhChapters.add(
                        HappyHourChapter(
                            title = chapterParts[1],
                            timeStamp = chapterParts[0],
                            uri = YoutubeChapterUrlAssembler.assemble(timestampString = chapterParts[0], videoId = videoId)
                        )
                    )
                }
                chapterParts.size > 2 -> {
                    val assembledTitle = chapterParts.subList(1, chapterParts.size)
                        .joinToString("-") { ch -> ch.trim() }
                    HappyHourChapter(
                        title = assembledTitle,
                        timeStamp = chapterParts[0],
                        uri = YoutubeChapterUrlAssembler.assemble(timestampString = chapterParts[0], videoId = videoId)
                    )
                }
                else -> {
                    Log.d(TAG, "toHappyHourChapterList: ")
                    throw IllegalStateException("size: $chapterParts")
                }
            }
        }

    }else {
        return emptyList()
    }
    return hhChapters
}

fun List<HappyHourVideoDto>.toHappyHourList(): List<HappyHour> {
    return this.map { it.toHappyHour() }
}


fun HappyHourVideoDto.toDatabaseEntity(): HappyHourEntity {
    return HappyHourEntity(
        id = this.id,
        part = this.part ?: 0,
        title = this.title ?: "",
        videoId = this.videoId ?: "",
        chapterString = this.timeStampText ?: "",
        publishedDate = this.publishedDate ?: "",
    )
}

fun HappyHourEntity.toModel(): HappyHour {
    return try {
        HappyHour(
            title = this.title,
            videoId = this.videoId,
            date = HappyHourDateFormatter.formatString(this.publishedDate),
            part = this.part,
            chapters = this.chapterString.toHappyHourChapterList(this.videoId)
        )
    } catch (e: Exception) {
        throw MappingException(e.cause)
    }
}
fun List<HappyHourVideoDto>.toDatabaseEntityList(): List<HappyHourEntity> {
    return this.map { it.toDatabaseEntity() }
}

fun List<HappyHourEntity>.toModelList(): List<HappyHour> {
    return this.map { it.toModel() }
}