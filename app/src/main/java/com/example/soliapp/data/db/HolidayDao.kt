package com.example.soliapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.soliapp.data.models.Holiday

@Dao
interface HolidayDao {

    @Query("SELECT * FROM holiday WHERE localName = :localName")
    fun getFavoriteByName(localName: String): Holiday?

    @Upsert
    fun saveFavorite(item: Holiday)

    @Delete
    fun removeFavorite(item: Holiday)
}
