package com.example.soliapp

import android.location.LocationProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HolidayViewModel @Inject constructor(
    private val repository: HolidayRepository,
    private val locationProvider: LocationProvider
) : ViewModel() {

    private val _state = MutableLiveData<LocationState>()
    val state: LiveData<LocationState>
        get() = _state

    fun fetchCountryCode() {
        viewModelScope.launch {
            _state.value = LocationState.Loading
            try {

                // Get the user's current location (country) from the GPS
                val countryCode = locationProvider.getCountryCode()

                // Get the current year
                val currentYear = Calendar.getInstance().get(Calendar.YEAR)

                // Fetch holidays for the current year and the determined country code
                val result = repository.getHolidays(currentYear, countryCode)

                _state.value = LocationState.Success(result)
            } catch (e: Exception) {
                _state.value = LocationState.Error("Failed to fetch location")
            }
        }
    }
}
