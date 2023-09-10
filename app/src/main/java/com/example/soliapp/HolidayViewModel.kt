package com.example.soliapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soliapp.data.CountryData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HolidayViewModel @Inject constructor(
    private val repository: IRepository,
    private val countryData: CountryData,
    private val currentYear: Int
) : ViewModel() {

    private val _state = MutableLiveData<LocationState>()
    val state: LiveData<LocationState>
        get() = _state

    fun fetchCountryCode() {
        viewModelScope.launch {
            _state.value = LocationState.Loading
            try {
                // Fetch holidays for the current year and the determined country code
                val result = repository.getHolidays(currentYear, countryData.cCode)
                _state.value = LocationState.Success(result)
            } catch (e: Exception) {
                _state.value = LocationState.Error("Failed to fetch location")
            }
        }
    }
}
