package org.furkan.kmmquickstart.core.presentation.navigation

sealed class NavigationEvent {
    object NavigateToLoginScreen : NavigationEvent()
    object NavigateToHomeScreen : NavigationEvent()
}
