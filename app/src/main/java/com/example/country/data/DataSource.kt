package com.example.country.data

import com.example.country.R
import com.example.country.model.Country

class DataSource () {
    fun loadCountry(): List<Country> {
        return listOf(
            Country(R.string.country1, R.string.capital1, R.string.desc1, "DZ", R.drawable.ic_flag_algerie),
            Country(R.string.country2, R.string.capital2, R.string.desc2, "AO", R.drawable.ic_flag_angola),
            Country(R.string.country3, R.string.capital3, R.string.desc3, "CG", R.drawable.ic_flag_congo),
            Country(R.string.country4, R.string.capital4, R.string.desc4, "GN", R.drawable.ic_flag_guinee),
            Country(R.string.country5, R.string.capital5, R.string.desc5, "MA", R.drawable.ic_flag_maroc)
        )
    }
}
