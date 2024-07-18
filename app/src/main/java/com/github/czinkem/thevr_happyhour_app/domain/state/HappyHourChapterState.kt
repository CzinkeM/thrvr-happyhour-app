package com.github.czinkem.thevr_happyhour_app.domain.state

import kotlinx.serialization.Serializable

@Serializable
data class HappyHourChapterState(
    val title: String,
    val uri: String,
    val timeStamp: String,
)