package com.github.czinkem.thevr_happyhour_app.domain.utils

object ThumbnailProvider {
    const val thumbnailEndpointTemplate = "https://img.youtube.com/vi/%s/0.jpg"

    fun provide(videoId: String): String {
        return thumbnailEndpointTemplate.format(videoId)
    }
}