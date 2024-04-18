package org.furkan.kmmquickstart.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.util.preference.KmmPreference
import splash.presentation.SplashContract
import splash.presentation.SplashViewModel

class AndroidSplashViewModel(
    private val sharedPref: KmmPreference
) : ViewModel() {

    private val viewModel by lazy {
        SplashViewModel(
            sharedPref = sharedPref,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: SplashContract.Event) {
        viewModel.onEvent(event)
    }
}