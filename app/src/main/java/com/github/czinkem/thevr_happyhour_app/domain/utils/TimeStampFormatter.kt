package com.github.czinkem.thevr_happyhour_app.domain.utils

object TimeStampFormatter {

    fun format(timeInSec: Int): String {
        val hours = timeInSec / 3600
        val minutes = (timeInSec % 3600) / 60
        val remainingSeconds = timeInSec % 60

        return String.format("%d:%02d:%02d", hours, minutes, remainingSeconds)
    }
}