package com.example.country.data

import com.example.country.R
import com.example.country.model.Country

class DataSource () {
    fun loadCountry(): List<Country> {
        return listOf(
            Country(
                name = "Algeria",
                capital = "Alger",
                code = "DZ",
                flagUrl = R.drawable.ic_flag_of_algerie,
                description = "L'Algérie est un pays d'Afrique du Nord faisant partie du Maghreb. C'est le plus grand pays du continent africain."
            ),
            Country(
                name = "Angola",
                capital = "Luanda",
                code = "AO",
                flagUrl = R.drawable.ic_flag_of_angola,
                description = "L'Angola est un pays d'Afrique centrale, limitrophe de la République démocratique du Congo, de la République du Congo, de la Namibie et de la Zambie."
            ),
            Country(
                name = "Congo DRC",
                capital = "Kinshasa",
                code = "CG",
                flagUrl = R.drawable.ic_flag_of_congo,
                description = "La République du Congo, également appelée Congo-Brazzaville, est un pays d'Afrique centrale."
            ),
            Country(
                name = "Guinea",
                capital = "Conakry",
                code = "GN",
                flagUrl = R.drawable.ic_flag_of_guinee,
                description = "La Guinée est un pays d'Afrique de l'Ouest. Elle est parfois appelée Guinée-Conakry pour la distinguer de la Guinée-Bissau et de la Guinée équatoriale."
            ),
            Country(
                name = "Maroc",
                capital = "Rabat",
                code = "MA",
                flagUrl = R.drawable.ic_flag_of_maroc,
                description = "Le Maroc est un pays situé au nord-ouest de l'Afrique. Il est bordé par l'océan Atlantique à l'ouest et par la mer Méditerranée au nord."
            )
            // Ajoutez les autres pays suivant le même modèle
        )
    }
}
