package com.github.czinkem.thevr_happyhour_app.data.offline

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.github.czinkem.thevr_happyhour_app.data.offline.entity.HappyHourEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HappyHourDao {

    @Upsert
    suspend fun upsertHappyHour(vararg happyHourEntity: HappyHourEntity)

    @Delete
    suspend fun delete(happyHourEntity: HappyHourEntity)

    @Query("SELECT * FROM happyhourentity ORDER BY part DESC")
    fun getHappyHour(): Flow<List<HappyHourEntity>>

}