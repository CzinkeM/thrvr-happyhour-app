package com.github.czinkem.thevr_happyhour_app.domain.utils

import android.net.Uri

object ThumbnailProvider {
    const val thumbnailEndpointTemplate = "https://img.youtube.com/vi/%s/0.jpg"

    fun thumbnailUrl(happyHourUrl: String): String {
        val uri = Uri.parse(happyHourUrl)
        val videoId = uri.getQueryParameter("v")
        return thumbnailEndpointTemplate.format(videoId)
    }
}