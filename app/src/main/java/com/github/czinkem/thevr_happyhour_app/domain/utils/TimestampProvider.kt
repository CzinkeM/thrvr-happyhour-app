package com.github.czinkem.thevr_happyhour_app.domain.utils

import android.util.Log
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private const val TAG = "TimestampProvider"
object TimestampProvider {

    private fun String.extractNumbersFromString(): List<Int> {
        val regex = "\\d+".toRegex()
        return regex.findAll(this).map { it.value.toInt() }.toList()
    }
    fun timestampString(timestamp: String): Int {
        try {
            return when {
                timestamp.count {it == ':'} == 3 -> {
                    val numbers = timestamp.extractNumbersFromString()
                    when(numbers.size) {
                        3 -> {
                            val hour = (numbers[0]) * 3600
                            val minute = (numbers[1]) * 60
                            val seconds = (numbers[2])
                            hour + minute + seconds
                        }
                        2 -> {
                            val minute = (numbers[0]) * 60
                            val seconds = (numbers[1])
                            minute + seconds
                        }
                        else -> {
                            throw IllegalStateException("numbers: $numbers")
                        }
                    }
                }
                timestamp.count { it == ':' } == 2 -> {
                    if(timestamp.matches(Regex("""\d{2}:\d{2}:\d{2}"""))) {
                        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                        val localTime = LocalTime.parse(timestamp.trim(), formatter)
                        localTime.toSecondOfDay()
                    }else {
                        val numbers = timestamp.extractNumbersFromString()
                        when(numbers.size) {
                            3 -> {
                                val hour = (numbers[0]) * 3600
                                val minute = (numbers[1]) * 60
                                val seconds = (numbers[2])
                                hour + minute + seconds
                            }
                            2 -> {
                                val minute = (numbers[0]) * 60
                                val seconds = (numbers[1])
                                minute + seconds
                            }
                            else -> {
                                throw IllegalStateException("numbers: $numbers")
                            }
                        }
                    }
                    // HH:mm:ss format

                }
                timestamp.count { it == ':' } == 1 -> {
                    val trimmedTimeStamp = timestamp.trim()
                    when {
                        trimmedTimeStamp.count() == 5 -> {
                            if(timestamp.matches(Regex("""\d{2}:\d{2}"""))) {
                                val parts = timestamp.split(":")
                                val minutes = parts[0].trim().toInt()
                                val seconds = parts[1].trim().toInt()
                                if(minutes != 0) {
                                    minutes * 60 + seconds
                                }else {
                                    seconds
                                }
                            }else {
                                val numbers = timestamp.extractNumbersFromString()
                                if(numbers.size == 2) {
                                    val minutes = (numbers[0]) * 60
                                    val seconds = (numbers[1])
                                    minutes + seconds
                                }else if(numbers.size == 3) {
                                    val hour = (numbers[0]) * 3600
                                    val minute = (numbers[1]) * 60
                                    val seconds = (numbers[2])
                                    hour + minute + seconds

                                } else {
                                    throw IllegalStateException("numbers: $numbers")
                                }
                            }
                        }
                        trimmedTimeStamp.count() > 5 -> {
                            val replacedTimestamp = timestamp.replace(' ', ':')
                            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                            val localTime = LocalTime.parse(replacedTimestamp, formatter)
                            localTime.toSecondOfDay()
                        }
                        else -> {
                            throw IllegalStateException()
                        }
                    }

                    // mm:ss format

                }
                timestamp.count { it == ':' } == 0-> {
                    0
                }
                else -> {
                    throw IllegalArgumentException("Invalid time format: $timestamp")
                }
            }
        }catch (e: Exception) {
            Log.d(TAG, "timestampString: ${e.cause} $e.")
            throw e
        }
    }
}