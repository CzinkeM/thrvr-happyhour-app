package com.github.czinkem.thevr_happyhour_app.data

interface IHappyHourRepository {

    fun getAll(): List<HappyHourDto>

    fun getBySerialNumber(serialNumber: Int): HappyHourDto?
}