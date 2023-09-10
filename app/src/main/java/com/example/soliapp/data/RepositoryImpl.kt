package com.example.soliapp.data

import com.example.soliapp.domain.IRepository
import com.example.soliapp.common.ResponseState
import com.example.soliapp.data.db.AppDatabase
import com.example.soliapp.data.network.HolidayApiService
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
    override suspend fun getHolidays(year: Int, countryCode: String): ResponseState {
        val deferred = CoroutineScope(Dispatchers.IO).async {
            holidayApiService.fetchHolidays(year, countryCode)
        }
        val response = deferred.await()

        return response
    }
}
