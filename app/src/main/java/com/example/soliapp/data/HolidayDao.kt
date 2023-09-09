package com.example.soliapp.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface HolidayDao {

    @Query("SELECT * FROM holiday")
    suspend fun getAllHolidays(): List<Holiday>

    // Other DAO methods for insert, update, delete, etc.
}
