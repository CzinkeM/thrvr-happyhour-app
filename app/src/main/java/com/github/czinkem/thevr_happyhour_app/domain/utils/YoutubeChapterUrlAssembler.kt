package com.github.czinkem.thevr_happyhour_app.domain.utils


object YoutubeChapterUrlAssembler {

    fun assemble(timestampString: String, videoId: String): String {
        val timeInDecimals = TimestampProvider.timestampString(timestampString)
        return "$YOUTUBE_BASE_URL/v=$videoId&t=${timeInDecimals}s"
    }
}