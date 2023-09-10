package com.example.soliapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "holiday")
data class Holiday(
//    val counties: String? = null,
    val countryCode: String,
    val date: String,
    val fixed: Boolean,
    val global: Boolean,
    val launchYear: Int? = null,
    @PrimaryKey val localName: String,
    val name: String,
    // Other properties
    var isFavorite: Boolean = false

): Parcelable
