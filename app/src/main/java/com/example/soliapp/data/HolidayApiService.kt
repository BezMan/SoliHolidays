package com.example.soliapp.data

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class HolidayApiService @Inject constructor(private val okHttpClient: OkHttpClient) {

    companion object {
        private const val BASE_URL = "https://date.nager.at"
    }

    fun fetchHolidays(year: Int, countryCode: String): Response {
        val url = "$BASE_URL/api/v3/publicholidays/$year/$countryCode"

        val request = Request.Builder()
            .url(url)
            .build()

        return okHttpClient.newCall(request).execute()
    }
}
