package com.example.soliapp

import com.example.soliapp.data.Holiday

interface HolidayRepository {
    suspend fun getHolidays(year: Int, countryCode: String): List<Holiday>
    // Other repository methods
}
