package com.example.soliapp.data

import androidx.room.Entity

@Entity(tableName = "holiday")
class HolidayList : ArrayList<Holiday>()

data class Holiday(
    val counties: String? = null,
    val countryCode: String,
    val date: String,
    val fixed: Boolean,
    val global: Boolean,
    val launchYear: Int? = null,
    val localName: String,
    val name: String,
    val types: List<String> = emptyList(),
    // Other properties
    val isFavorite: Boolean = false

)
