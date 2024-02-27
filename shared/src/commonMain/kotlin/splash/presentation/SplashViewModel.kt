package splash.presentation

import core.Constants
import core.domain.util.preference.KmmPreference
import core.domain.util.toCommonStateFlow
import core.presentation.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import login.presentation.LoginContract
import kotlin.time.Duration.Companion.seconds

class SplashViewModel(
    private val sharedPref: KmmPreference,
    private val coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(SplashContract.State())
    val state = _state.asStateFlow().toCommonStateFlow()


    init {
        viewModelScope.launch {
            delay(1.seconds)
            val loginFlag = sharedPref.getBool(Constants.KEY_LOGIN_FLAG, false)
            if (loginFlag) {
                _state.update {
                    it.copy(
                        currentRoute = Routes.LOGIN
                    )
                }
            }
            else {
                _state.update {
                    it.copy(
                        currentRoute = Routes.LOGIN
                    )
                }
            }


        }
    }

    fun onEvent(event: SplashContract.Event) {
        when (event) {
            is SplashContract.Event.NavigateToHomeScreen -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(currentRoute = Routes.HOME)
                    }
                }
            }

            is SplashContract.Event.NavigateToLoginScreen -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(currentRoute = Routes.LOGIN)
                    }
                }
            }

        }
    }


}