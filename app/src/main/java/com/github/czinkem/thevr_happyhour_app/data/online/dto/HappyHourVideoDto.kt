package com.github.czinkem.thevr_happyhour_app.data.online.dto

import com.google.gson.annotations.SerializedName

data class HappyHourVideoDto(
    @SerializedName("id"            ) var id            : Int?    = null,
    @SerializedName("part"          ) var part          : Int?    = null,
    @SerializedName("title"         ) var title         : String? = null,
    @SerializedName("videoId"       ) var videoId       : String? = null,
    @SerializedName("videoCoverImg" ) var videoCoverImg : String? = null,
    @SerializedName("timeStampText" ) var timeStampText : String? = null,
    @SerializedName("publishedDate" ) var publishedDate : String? = null

)
