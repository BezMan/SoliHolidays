package com.example.soliapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

class HolidayList : ArrayList<Holiday>()

@Parcelize
@Entity(tableName = "holiday")
data class Holiday(
    val counties: String? = null,
    val countryCode: String,
    val date: String,
    val fixed: Boolean,
    val global: Boolean,
    val launchYear: Int? = null,
    val localName: String,
    @PrimaryKey val name: String,
    // Other properties
    val isFavorite: Boolean = false

): Parcelable
