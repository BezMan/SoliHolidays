package com.example.soliapp.common

import com.example.soliapp.data.models.Holiday

sealed class ResponseState {
    data class Success(val holidayList: List<Holiday>) : ResponseState()
    object Loading : ResponseState()
    data class Error(val errorMessage: String) : ResponseState()
}