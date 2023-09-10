package com.example.soliapp.data.network

import com.example.soliapp.data.models.Holiday
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

    fun fetchHolidays(year: Int, countryCode: String): List<Holiday>? {
        val url = "$BASE_URL/api/v3/publicholidays/$year/$countryCode"

        val request = Request.Builder().url(url).build()

        val response = okHttpClient.newCall(request).execute()
        val responseBody = response.body
        if (response.isSuccessful && responseBody != null) {
            val bodyString = responseBody.source().readString(Charsets.UTF_8)

            if (bodyString.isNotBlank()) {
                // Parse the JSON string into a JSON array
                val jsonArray: JsonArray = JsonParser.parseString(bodyString).asJsonArray

                val holidayList = mutableListOf<Holiday>()
                // Iterate through the JSON array
                for (jsonElement in jsonArray) {
                    // Convert each JSON element to a JSON object and add it to the list
                    val holiday = Gson().fromJson(jsonElement, Holiday::class.java)
                    holidayList.add(holiday)
                }

                return holidayList
            }

        }
        // Handle API error
        return null
    }
}
