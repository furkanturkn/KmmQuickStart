package org.furkan.kmmquickstart.login

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import login.presentation.LoginContract
import org.furkan.kmmquickstart.SharedRes
import org.furkan.kmmquickstart.core.presentation.components.LoadingAnimation
import org.furkan.kmmquickstart.core.presentation.components.sharedStringResource
import org.furkan.kmmquickstart.core.presentation.components.standart_button.StandardButton
import org.furkan.kmmquickstart.core.presentation.navigation.CoreNavigator
import org.furkan.kmmquickstart.core.presentation.navigation.NavigationEvent
import org.furkan.kmmquickstart.core.presentation.theme.AppTheme
import org.furkan.kmmquickstart.login.components.PhoneNumberSection
import org.furkan.kmmquickstart.login.components.TopSection

@Composable
fun LoginScreen(
    state: LoginContract.State,
    onEvent: (LoginContract.Event) -> Unit = {},
    navigator: CoreNavigator
) {
    LaunchedEffect(key1 = state.currentRoute) {
        state.currentRoute?.let {
            navigator.navigate(NavigationEvent.NavigateToHomeScreen)
        }

    }

    ScreenContent(
        state = state,
        onEvent = onEvent
    )

    if (state.isLoadingShowing) {
        LoadingAnimation()
    }

}

@Composable
private fun ScreenContent(
    state: LoginContract.State,
    onEvent: (LoginContract.Event) -> Unit = {}
) {
    val localFocusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                localFocusManager.clearFocus()
            })
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
        ) {
            TopSection()
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.7f)
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Spacer(modifier = Modifier.size(32.dp))

                PhoneNumberSection(
                    state = state,
                    onEvent = onEvent
                )

                Spacer(modifier = Modifier.size(16.dp))

                StandardButton(
                    text = sharedStringResource(
                        id = SharedRes.strings.login
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onEvent(LoginContract.Event.LoginClicked)
                    })

            }
        }

    }

}


@Preview
@Composable
fun WelcomeScreenPreview() {
    AppTheme {
        LoginScreen(
            state = LoginContract.State(),
            onEvent = {},
            navigator = CoreNavigator.mock
        )
    }
}
