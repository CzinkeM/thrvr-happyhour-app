package com.github.czinkem.thevr_happyhour_app.data.offline.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
@TypeConverters(HappyHourChapterConverter::class)
data class HappyHourEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var part: Int,
    var title: String,
    var videoId: String,
    var videoCoverImg: String,
    var chapters: List<HappyHourChapterEntity>,
    var publishedDate: String
)