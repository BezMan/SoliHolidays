package com.example.soliapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soliapp.domain.IRepository
import com.example.soliapp.common.ResponseState
import com.example.soliapp.data.models.CountryData
import com.example.soliapp.data.models.Holiday
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HolidayViewModel @Inject constructor(
    private val repository: IRepository,
    private val countryData: CountryData,
    private val currentYear: Int
) : ViewModel() {

    private val _state = MutableLiveData<ResponseState>()
    val state: LiveData<ResponseState>
        get() = _state

    fun fetchData() {
        viewModelScope.launch {
            _state.value = ResponseState.Loading
            // Fetch holidays for the current year and the determined country code
            _state.value = repository.getHolidays(currentYear, countryData.cCode)
        }
    }

    fun saveFavorite(item: Holiday) {
        viewModelScope.launch {
            repository.saveFavorite(item)
        }
    }

    fun removeFavorite(item: Holiday) {
        viewModelScope.launch {
            repository.removeFavorite(item)
        }
    }

}
