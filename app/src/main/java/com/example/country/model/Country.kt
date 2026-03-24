package com.example.country.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Country(
    @StringRes val nameResourceId: Int,
    @StringRes val capitalResourceId: Int,
    val code: String,
    @DrawableRes val flagResourceId: Int
)
