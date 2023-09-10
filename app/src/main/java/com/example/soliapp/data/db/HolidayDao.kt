package com.example.soliapp.data.db

import androidx.room.Dao
import androidx.room.Query
import com.example.soliapp.data.models.Holiday
import kotlinx.coroutines.flow.Flow

@Dao
interface HolidayDao {

    @Query("SELECT * FROM holiday")
    fun getAllHolidays(): Flow<List<Holiday>>

    // Other DAO methods for insert, update, delete, etc.
}
