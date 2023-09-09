package com.example.soliapp.data

import androidx.room.Entity

@Entity(tableName = "holiday")

data class HolidayList(
    val holidayList: List<Holiday>
)

data class Holiday(
    val date: String,
    val localName: String,
    val name: String,
    val countryCode: String,
    val fixed: Boolean,
    val global: Boolean,
    // Other properties
)
