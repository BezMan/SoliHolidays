package com.example.soliapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soliapp.data.models.Holiday
import kotlinx.coroutines.flow.Flow

@Dao
interface HolidayDao {

    @Query("SELECT * FROM holiday")
    fun getAllHolidays(): Flow<List<Holiday>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavorite(item: Holiday)

    @Delete
    fun removeFavorite(item: Holiday)


    // Other DAO methods for insert, update, delete, etc.
}
