package com.github.czinkem.thevr_happyhour_app.data

import retrofit2.Call
import retrofit2.http.GET

interface HappyHourApi {
    @GET("/happy-hours/")
    fun getAll(): Call<List<HappyHourDto>>
}