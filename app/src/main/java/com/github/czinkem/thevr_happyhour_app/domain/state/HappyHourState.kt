package com.github.czinkem.thevr_happyhour_app.domain.state

import kotlinx.serialization.Serializable

@Serializable
data class HappyHourState(
    val title: String,
    val url: String,
    val date: String,
    val serialNumber: Int,
    val chapters: List<HappyHourChapterState>
)
