package com.example.soliapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Holiday::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun holidayDao(): HolidayDao
}