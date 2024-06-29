package com.github.czinkem.thevr_happyhour_app.domain.utils

object HappyHourSerialNumberValidator {

    fun isValid(serialNumberString: String, min: Int = 1, max: Int): Boolean {
        return try {
            val serialNumber = serialNumberString.toInt()
            serialNumber in min..max
        } catch (e: NumberFormatException) {
            false
        }
    }
}