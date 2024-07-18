package com.github.czinkem.thevr_happyhour_app.domain.utils

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object TimestampProvider {
    fun timestampString(timestamp: String): Int {
        return when {
            timestamp.count { it == ':' } == 2 -> {
                // HH:mm:ss format
                val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                val localTime = LocalTime.parse(timestamp.trim(), formatter)
                localTime.toSecondOfDay()
            }
            timestamp.count { it == ':' } == 1 -> {
                // mm:ss format
                val parts = timestamp.split(":")
                val minutes = parts[0].trim().toInt()
                val seconds = parts[1].trim().toInt()
                if(minutes != 0) {
                    minutes * 60 + seconds
                }else {
                    seconds
                }
            }
            else -> {
                throw IllegalArgumentException("Invalid time format: $timestamp")
            }
        }
    }
}