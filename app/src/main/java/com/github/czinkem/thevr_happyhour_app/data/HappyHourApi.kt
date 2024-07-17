package com.github.czinkem.thevr_happyhour_app.data

import com.github.czinkem.thevr_happyhour_app.data.dto.HappyHourDto
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface HappyHourApi {
    @POST(HttpRoutes.HAPPYHOURS)
    fun getPage(
        @Body request: RequestBody
    ): Call<HappyHourDto>
}