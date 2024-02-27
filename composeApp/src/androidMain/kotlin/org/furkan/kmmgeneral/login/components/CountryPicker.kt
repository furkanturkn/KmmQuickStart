package org.furkan.kmmquickstart.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import login.domain.model.Country
import login.domain.model.countryTurkey
import org.furkan.kmmquickstart.R
import org.furkan.kmmquickstart.SharedRes
import org.furkan.kmmquickstart.core.presentation.components.BottomSheet
import org.furkan.kmmquickstart.core.presentation.components.sharedStringResource
import org.furkan.kmmquickstart.core.presentation.components.standart_textfield.CustomTextField
import org.furkan.kmmquickstart.core.presentation.theme.AppTheme
import org.furkan.kmmquickstart.core.presentation.theme.FaintTextColor
import org.furkan.kmmquickstart.core.presentation.theme.TextFieldHintColor

@Composable
fun CountrySelection(
    selectedCountry: Country,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .height(60.dp)
            .clickable(enabled) { onClick() }
    ) {
        Text(
            text = "${selectedCountry.toFlagEmoji()} +${selectedCountry.phoneCode}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun CountrySelectionSheet(
    onDismiss: () -> Unit,
    countries: List<Country>,
    onCountrySelect: (Country) -> Unit,
) {
    BottomSheet(
        onDismiss = { onDismiss() },
    ) {
        CountrySelectionSheetContent(countries = countries, onCountrySelect = onCountrySelect)
    }
}

@Composable
fun CountrySelectionSheetContent(
    countries: List<Country>,
    onCountrySelect: (Country) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    val filteredCountries = countries.filter { country ->
        country.hasText(searchText)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .fillMaxHeight(0.95f)
            .padding(24.dp)
    ) {
        CustomTextField(
            textFieldState = searchText,
            onValueChange = { searchText = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = sharedStringResource(id = SharedRes.strings.app_logo_alt),
                    tint = TextFieldHintColor,
                    modifier = Modifier.size(16.dp)
                )
            },
            labelText = sharedStringResource(id = SharedRes.strings.search_country_code)
        )

        Spacer(modifier = Modifier.height(26.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(filteredCountries) {
                CountrySelectionItem(
                    country = it,
                    modifier = Modifier.clickable { onCountrySelect(it) }
                )

                Divider()
            }
        }
    }
}

@Composable
fun CountrySelectionItem(
    country: Country,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "${country.toFlagEmoji()} ${country.name}",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Normal
        )

        Text(
            text = "+${country.phoneCode}",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Normal,
            color = FaintTextColor
        )
    }
}

@Preview
@Composable
fun CountrySelectionPreview() {
    AppTheme {
        CountrySelection(
            selectedCountry = countryTurkey,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CountrySelectionItemPreview() {
    AppTheme {
        CountrySelectionItem(country = countryTurkey)
    }
}
