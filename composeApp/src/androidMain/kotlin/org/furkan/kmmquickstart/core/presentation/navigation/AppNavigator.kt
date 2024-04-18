package org.furkan.kmmquickstart.core.presentation.navigation

import androidx.navigation.NavHostController
import core.presentation.Routes

class AppNavigator(private val navController: NavHostController) : CoreNavigator {
    override fun navigate(event: NavigationEvent) {
        when (event) {
            NavigationEvent.NavigateToHomeScreen -> {
                navController.popBackStack()
                navController.navigate(
                    Routes.HOME
                )
            }

            NavigationEvent.NavigateToLoginScreen -> {
                navController.popBackStack()
                navController.navigate(
                    Routes.LOGIN
                )
            }
        }
    }
}
