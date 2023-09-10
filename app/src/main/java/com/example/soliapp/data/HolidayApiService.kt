package com.example.soliapp.data

import com.example.soliapp.LocationState
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
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

// Parse the JSON string into a JSON array
            val jsonArray: JsonArray = JsonParser.parseString(bodyString).asJsonArray

            val holidayList = mutableListOf<Holiday>()
// Iterate through the JSON array
            for (jsonElement in jsonArray) {
                // Convert each JSON element to a JSON object and add it to the list
                val holiday = Gson().fromJson(jsonElement, Holiday::class.java)
//                val jsonObject = jsonElement.asJsonObject
                holidayList.add(holiday)
            }

            // Store holidays in Room database if needed
            //

            return LocationState.Success(holidayList)
        } else {
//                 Handle API error
            return LocationState.Error("bad response")
        }
    }
}
