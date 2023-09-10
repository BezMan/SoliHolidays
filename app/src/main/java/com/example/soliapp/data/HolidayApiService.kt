package com.example.soliapp.data

import com.example.soliapp.LocationState
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class HolidayApiService @Inject constructor(private val okHttpClient: OkHttpClient) {

    companion object {
        private const val BASE_URL = "https://date.nager.at"
    }

    fun fetchHolidays(year: Int, countryCode: String): LocationState {
        val url = "$BASE_URL/api/v3/publicholidays/$year/$countryCode"

        val request = Request.Builder()
            .url(url)
            .build()

        val response = okHttpClient.newCall(request).execute()
        if (response.isSuccessful) {
            val bodyString = response.body?.source()?.readString(Charsets.UTF_8)

            val holidayList = Gson().fromJson(bodyString, HolidayList::class.java)

            // Store holidays in Room database if needed
            //

            return LocationState.Success(holidayList)
        } else {
//                 Handle API error
            return LocationState.Error("bad response")
        }
    }
}
