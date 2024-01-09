package com.example.soliapp.data

import com.example.soliapp.common.ResponseState
import com.example.soliapp.data.db.AppDatabase
import com.example.soliapp.data.models.Holiday
import com.example.soliapp.data.network.HolidayApiService
import com.example.soliapp.domain.IRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val holidayApiService: HolidayApiService,
    private val appDatabase: AppDatabase,
    private val coroutineDispatcher: CoroutineDispatcher
) : IRepository {

    override suspend fun getHolidays(year: Int, countryCode: String): ResponseState {
        return try {
            val holidayList = withContext(coroutineDispatcher) {
                // Perform network request in IO dispatcher
                holidayApiService.fetchHolidays(year, countryCode)
            }

            if (holidayList == null) {
                ResponseState.Error("Error with response")
            } else {
                withContext(coroutineDispatcher) {
                    // Perform database queries in IO dispatcher
                    syncDataWithStorage(holidayList)
                    ResponseState.Success(holidayList)
                }

            }
        } catch (e: Exception) {
            ResponseState.Error("Error: ${e.message}")
        }
    }

    private fun syncDataWithStorage(holidayList: List<Holiday>) {
        holidayList.forEach { holiday ->
            val favoriteByName = appDatabase.getFavoriteByName(holiday.localName)
            if (favoriteByName != null && favoriteByName.isFavorite) {
                holiday.isFavorite = true
            }
        }
    }

    override suspend fun saveFavorite(item: Holiday) {
        CoroutineScope(coroutineDispatcher).launch {
            appDatabase.saveFavorite(item)
        }
    }

    override suspend fun removeFavorite(item: Holiday) {
        CoroutineScope(coroutineDispatcher).launch {
            appDatabase.removeFavorite(item)
        }
    }
}
