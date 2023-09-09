package com.example.soliapp

import com.example.soliapp.data.Holiday

sealed class LocationState {
    data class Success(val holidayList: List<Holiday>) : LocationState()
    object Loading : LocationState()
    data class Error(val errorMessage: String) : LocationState()
}