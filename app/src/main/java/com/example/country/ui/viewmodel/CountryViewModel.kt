package com.example.country.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.country.model.Country
import com.example.country.model.CountryApi
import com.example.country.model.CountryResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val _allCountries = MutableStateFlow<List<Country>>(emptyList())
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val api = CountryApi.create()

    // Un seul StateFlow pour gérer la liste affichée (filtrée ou non)
    val countries: StateFlow<List<Country>> = combine(_allCountries, _searchQuery) { list, query ->
        if (query.isBlank()) {
            list
        } else {
            list.filter { 
                it.name.contains(query, ignoreCase = true) || 
                it.capital.contains(query, ignoreCase = true) ||
                it.code.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun fetchAllCountries() {
        fetch { api.getAllCountries() }
    }

    fun fetchAfricanCountries() {
        fetch { api.getCountriesByRegion("africa") }
    }

    private fun fetch(apiCall: suspend () -> List<CountryResponse>) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                Log.d("CountryViewModel", "Fetching countries...")
                val response = apiCall()
                Log.d("CountryViewModel", "Fetched ${response.size} countries")
                _allCountries.value = response.map { res ->
                    Country(
                        name = res.name?.common ?: "N/A",
                        capital = res.capital?.firstOrNull() ?: "N/A",
                        code = res.code ?: "N/A",
                        flagUrl = res.flags?.png ?: "",
                        description = "Région: ${res.region ?: "N/A"}"
                    )
                }.sortedBy { it.name }
            } catch (e: Exception) {
                Log.e("CountryViewModel", "Error fetching countries: ${e.message}", e)
                _allCountries.value = emptyList() // Clear list on error
            } finally {
                _isLoading.value = false
            }
        }
    }
}
