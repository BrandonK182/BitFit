package com.example.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BitFitDao {
    @Query("SELECT * FROM running_table")
    fun getAll(): Flow<List<RunningEntity>>

    @Insert
    fun insertAll(runningData: List<RunningEntity>)

    @Query("DELETE FROM running_table")
    fun deleteAll()
}