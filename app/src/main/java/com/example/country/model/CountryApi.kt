package com.example.country.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {
    @GET("all?fields=name,capital,flags,cca2,region")
    suspend fun getAllCountries(): List<CountryResponse>

    @GET("region/{region}?fields=name,capital,flags,cca2,region")
    suspend fun getCountriesByRegion(@Path("region") region: String): List<CountryResponse>

    companion object {
        private const val BASE_URL = "https://restcountries.com/v3.1/"
        
        fun create(): CountryApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CountryApi::class.java)
        }
    }
}
