package org.furkan.kmmquickstart.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import login.domain.use_case.ValidatePhoneNumberUseCase
import login.presentation.LoginContract
import login.presentation.LoginViewModel

class AndroidLoginViewModel(
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase
) : ViewModel() {

    private val viewModel by lazy {
        LoginViewModel(
            validatePhoneNumberUseCase = validatePhoneNumberUseCase,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: LoginContract.Event) {
        viewModel.onEvent(event)
    }
}