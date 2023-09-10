package com.example.soliapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soliapp.data.models.Holiday

@Dao
interface HolidayDao {

    @Query("SELECT * FROM holiday WHERE localName = :localName")
    fun getFavoriteByName(localName: String): Holiday?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavorite(item: Holiday)

    @Delete
    fun removeFavorite(item: Holiday)
}
