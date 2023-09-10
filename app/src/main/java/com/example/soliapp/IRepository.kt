package com.example.soliapp

interface IRepository {

    suspend fun getHolidays(year: Int, countryCode: String): LocationState
    // Other repository methods
}
