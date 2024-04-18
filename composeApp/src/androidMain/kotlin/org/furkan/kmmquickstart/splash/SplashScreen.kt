package org.furkan.kmmquickstart.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import core.presentation.Routes
import org.furkan.kmmquickstart.core.presentation.navigation.CoreNavigator
import org.furkan.kmmquickstart.core.presentation.navigation.NavigationEvent
import splash.presentation.SplashContract

@Composable
fun SplashScreen(
    state: SplashContract.State,
    navigator: CoreNavigator
) {

    LaunchedEffect(key1 = state.currentRoute) {
        state.currentRoute?.let {
            when (it) {
                Routes.LOGIN -> navigator.navigate(NavigationEvent.NavigateToLoginScreen)
                Routes.HOME -> navigator.navigate(NavigationEvent.NavigateToHomeScreen)
            }

        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = org.furkan.kmmquickstart.shared.R.drawable.test),
            contentDescription = stringResource(id = org.furkan.kmmquickstart.shared.R.string.app_logo_alt)
        )

    }

}