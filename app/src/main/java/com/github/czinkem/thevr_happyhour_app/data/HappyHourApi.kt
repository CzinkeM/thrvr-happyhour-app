package com.github.czinkem.thevr_happyhour_app.data

import retrofit2.http.GET

interface HappyHourApi {
    @GET("happy-hours/")
    fun getAll(): List<HappyHourDto>
}