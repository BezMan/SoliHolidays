package com.example.soliapp

import com.example.soliapp.data.AppDatabase
import com.example.soliapp.data.Holiday
import com.example.soliapp.data.HolidayApiService
import com.example.soliapp.data.HolidayList
import com.google.gson.Gson
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val holidayApiService: HolidayApiService,
    private val appDatabase: AppDatabase
) : IRepository {
    override suspend fun getHolidays(year: Int, countryCode: String): List<Holiday> {
        try {
            val response = holidayApiService.fetchHolidays(year, countryCode)
            if (response.isSuccessful) {

                val json = response.toString()

                val holidayList = Gson().fromJson(json, HolidayList::class.java)

                // Store holidays in Room database if needed
                return holidayList
            } else {
                // Handle API error
                return emptyList()
            }
        } catch (e: IOException) {
            // Handle network error
            return emptyList()
        }
    }
}
