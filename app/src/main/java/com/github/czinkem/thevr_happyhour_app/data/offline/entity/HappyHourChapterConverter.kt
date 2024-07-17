package com.github.czinkem.thevr_happyhour_app.data.offline.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class HappyHourChapterConverter {
    @TypeConverter
    fun fromString(value: String?): List<HappyHourChapterEntity> {
        val listType: Type = object : TypeToken<List<HappyHourChapterEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<HappyHourChapterEntity>): String {
        val gson = Gson()
        val json = gson.toJson(list)
        return json
    }
}