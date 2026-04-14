package com.example.country.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.country.R
import com.example.country.model.Country
import com.example.country.ui.viewmodel.CountryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryApp(viewModel: CountryViewModel = viewModel()) {
    val countries by viewModel.countries.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Liste des Pays") })
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(countries) { country ->
                CountryItem(country)
            }
        }
    }
}

@Composable
fun CountryItem(country: Country) {
    Log.i("CountryItem", "CountryItem: ${country.flagUrl}")
    Row(modifier = Modifier.padding(8.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(country.flagUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Country Flag",
            error = painterResource(R.drawable.error),
            placeholder = painterResource(R.drawable.load),
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = country.name, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Capitale: ${country.capital}")
            Text(text = "Code: ${country.code}")
            if (country.description.isNotEmpty()) {
                Text(text = country.description, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
