package com.example.soliapp

import com.example.soliapp.data.AppDatabase
import com.example.soliapp.data.HolidayApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val holidayApiService: HolidayApiService,
    private val appDatabase: AppDatabase
) : IRepository {
    override suspend fun getHolidays(year: Int, countryCode: String): LocationState {
        val deferred = CoroutineScope(Dispatchers.IO).async {
            holidayApiService.fetchHolidays(year, countryCode)
        }
        val response = deferred.await()

        return response
    }
}
