package com.github.czinkem.thevr_happyhour_app.data.dto

import com.google.gson.annotations.SerializedName

data class HappyHourDto(
    @SerializedName("hh_videos" ) var hhVideos : ArrayList<HappyHourVideoDto> = arrayListOf(),
    @SerializedName("page"      ) var page     : Int?                = null
)
