package login.presentation

import core.util.AuthError
import login.domain.model.Country
import login.domain.model.countryTurkey

interface LoginContract {

    data class State(
        val countries: List<Country>? = emptyList(),
        val showOtpBottomSheet: Boolean = false,
        val phoneNumber: String = "05303718349",
        val phoneNumberError: AuthError? = null,
        val selectedPhoneNumberCountry: Country = countryTurkey,
        val isCountrySelectionSheetVisible: Boolean = false,
        val currentRoute: String? = null,
        val isLoadingShowing: Boolean = false
    )

    sealed class Event {
        data object NavigateToOtp : Event()
        data object NavigateToHome : Event()
        data class PhoneNumberUpdated(val value: String) : Event()
        data object LoginClicked : Event()
        data object DismissCountrySelectionSheet : Event()
        data object ShowCountrySelectionSheet : Event()
        data class UpdatePhoneNumberCountry(val country: Country) : Event()
    }

}
