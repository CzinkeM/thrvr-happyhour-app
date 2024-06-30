package com.github.czinkem.thevr_happyhour_app.domain.utils

import android.net.Uri

object TimestampProvider {
    fun timestampString(happyHourUrl: String): String {
        val uri = Uri.parse(happyHourUrl)
        val timestamp = uri.getQueryParameter("t")
        return TimeStampFormatter.format(timestamp!!.removeSuffix("s").toInt())
    }
}