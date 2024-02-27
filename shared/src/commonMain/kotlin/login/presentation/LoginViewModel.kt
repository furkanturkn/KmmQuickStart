package login.presentation

import core.domain.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import login.domain.use_case.ValidatePhoneNumberUseCase
import login.presentation.LoginContract.Event.LoginClicked

class LoginViewModel(
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(LoginContract.State())
    val state = _state.asStateFlow().toCommonStateFlow()

    init {
        viewModelScope.launch {
            /*
            val loginFlag = sharedPref.getBool(Constants.KEY_LOGIN_FLAG, false)
            if (loginFlag) {
                _state.update {
                    it.copy(
                        currentRoute = "home"
                    )
                }
            }

             */

        }
    }

    fun onEvent(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.PhoneNumberUpdated -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(phoneNumber = event.value)
                    }
                }
            }

            is LoginClicked -> {
                showLoading()
                viewModelScope.launch {
                    val error = validatePhoneNumberUseCase(_state.value.phoneNumber)
                    _state.update {
                        it.copy(
                            phoneNumberError = error
                        )
                    }

                    if (error == null) {

                        hideLoading()

                        /*
                            when (result) {
                                is Resource.Success -> {

                                    result.data?.let { userDto ->

                                        if (_state.value.password == userDto.data.userPassword) {
                                            if (userDto.data.userIsActive.not()) {
                                                //TODO: APPLY USER ACTIVATION
                                                //if activation success
                                                sharedPref.put(Constants.KEY_LOGIN_FLAG, true)
                                                navigateTo("home")
                                            } else {
                                                sharedPref.put(Constants.KEY_LOGIN_FLAG, true)
                                                navigateTo("home")
                                            }

                                        }
                                    }


                                }

                                is Resource.Error -> {
                                    println("error: ${result.data?.errorMessage}")
                                }
                            }


                         */

                    }

                }
            }

            is LoginContract.Event.ShowCountrySelectionSheet -> {
                showCountrySelectionSheet()
            }

            is LoginContract.Event.DismissCountrySelectionSheet -> {
                dismissCountrySelectionSheet()
            }

            is LoginContract.Event.UpdatePhoneNumberCountry -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            selectedPhoneNumberCountry = event.country
                        )
                    }
                }
            }

            else -> Unit

        }
    }

    private fun navigateTo(route: String?) {
        viewModelScope.launch {
            _state.update {
                it.copy(currentRoute = route)
            }
        }
    }

    private fun showLoading() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoadingShowing = true)
            }
        }
    }

    private fun hideLoading() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoadingShowing = false)
            }
        }
    }

    private fun showCountrySelectionSheet() {
        viewModelScope.launch {
            _state.update {
                it.copy(isCountrySelectionSheetVisible = true)
            }
        }
    }

    private fun dismissCountrySelectionSheet() {
        viewModelScope.launch {
            _state.update {
                it.copy(isCountrySelectionSheetVisible = false)
            }
        }
    }

}