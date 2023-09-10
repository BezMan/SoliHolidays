package com.example.soliapp.domain

import com.example.soliapp.common.ResponseState

interface IRepository {

    suspend fun getHolidays(year: Int, countryCode: String): ResponseState
    // Other repository methods
}
