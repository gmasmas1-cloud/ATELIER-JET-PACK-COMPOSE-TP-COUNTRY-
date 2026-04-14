package com.example.country.model

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    val name: CountryName?,
    val capital: List<String>?,
    val flags: CountryFlags?,
    @SerializedName("cca2") val code: String?,
    val region: String?
)

data class CountryName(
    val common: String?,
    val official: String?
)

data class CountryFlags(
    val png: String?,
    val svg: String?
)

// UI Model
data class Country(
    val name: String,
    val capital: String,
    val code: String,
    val flagUrl: Any, // Changed to Any to support both URLs (String) and Resources (Int)
    val description: String = ""
)
