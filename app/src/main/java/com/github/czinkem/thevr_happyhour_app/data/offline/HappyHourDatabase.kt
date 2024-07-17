package com.github.czinkem.thevr_happyhour_app.data.offline

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.czinkem.thevr_happyhour_app.data.offline.entity.HappyHourEntity

@Database(
    entities = [HappyHourEntity::class],
    version = 1,
)
abstract class HappyHourDatabase: RoomDatabase() {
     abstract val dao: HappyHourDao
}