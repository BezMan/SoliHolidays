package com.example.soliapp.domain

import com.example.soliapp.common.ResponseState
import com.example.soliapp.data.models.Holiday

interface IRepository {

    suspend fun getHolidays(year: Int, countryCode: String): ResponseState
    suspend fun saveFavorite(item: Holiday)
    suspend fun removeFavorite(item: Holiday)
}
