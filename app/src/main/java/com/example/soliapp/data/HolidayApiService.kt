package com.example.soliapp.data

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class HolidayApiService @Inject constructor(private val okHttpClient: OkHttpClient) {

    fun fetchHolidays(year: Int, countryCode: String): Response {
        val url = "https://date.nager.at/api/v3/publicholidays/$year/$countryCode"

        val request = Request.Builder()
            .url(url)
            .build()

        return okHttpClient.newCall(request).execute()
    }
}
