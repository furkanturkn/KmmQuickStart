package org.furkan.kmmquickstart.login.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import login.domain.model.countryTurkey
import login.domain.model.countryUSA
import login.presentation.LoginContract
import org.furkan.kmmquickstart.R
import org.furkan.kmmquickstart.SharedRes
import org.furkan.kmmquickstart.core.presentation.components.sharedStringResource
import org.furkan.kmmquickstart.core.presentation.components.standart_textfield.CustomTextField

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhoneNumberSection(
    state: LoginContract.State,
    onEvent: (LoginContract.Event) -> Unit = {}
) {
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    Row {
        CountrySelection(
            selectedCountry = state.selectedPhoneNumberCountry,
            onClick = { onEvent(LoginContract.Event.ShowCountrySelectionSheet) }
        )

        if(state.isCountrySelectionSheetVisible){
            CountrySelectionSheet(
                onDismiss = { onEvent(LoginContract.Event.DismissCountrySelectionSheet)  },
                countries = listOf(countryTurkey, countryUSA),
                onCountrySelect = {
                    onEvent(LoginContract.Event.UpdatePhoneNumberCountry(it))
                    onEvent(LoginContract.Event.DismissCountrySelectionSheet)
                }
            )
        }


        Spacer(modifier = Modifier.width(8.dp))

        CustomTextField(
            textFieldState = state.phoneNumber,
            labelText = sharedStringResource(id = SharedRes.strings.phone_number_or_email_hint),
            onValueChange = { onEvent(LoginContract.Event.PhoneNumberUpdated(it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            maxLength = 30,
            modifier = Modifier.onFocusEvent {
                if (it.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }
        )
    }
}

@Composable
@Preview
fun PhoneNumberSectionPreview() {
    PhoneNumberSection(
        state = LoginContract.State(),
        onEvent = {}
    )
}
