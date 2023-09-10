package com.example.soliapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.soliapp.data.models.Holiday

@Database(entities = [Holiday::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun holidayDao(): HolidayDao

    fun saveFavorite(item: Holiday) {
        holidayDao().saveFavorite(item)
    }

    fun removeFavorite(item: Holiday) {
        holidayDao().removeFavorite(item)
    }
}
