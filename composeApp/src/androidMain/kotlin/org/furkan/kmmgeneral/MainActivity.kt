package org.furkan.kmmquickstart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import core.presentation.Routes
import org.furkan.kmmquickstart.core.presentation.navigation.AppNavigator
import org.furkan.kmmquickstart.core.presentation.theme.AppTheme
import org.furkan.kmmquickstart.login.AndroidLoginViewModel
import org.furkan.kmmquickstart.login.LoginScreen
import org.furkan.kmmquickstart.splash.AndroidSplashViewModel
import org.furkan.kmmquickstart.splash.SplashScreen
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppRoute()
                }
            }
        }
    }
}

@Composable
fun AppRoute() {
    val navController = rememberNavController()
    val navigator = AppNavigator(navController)

    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            val splashViewModel = get<AndroidSplashViewModel>()
            val state by splashViewModel.state.collectAsState()
            SplashScreen(
                state = state,
                navigator = navigator
            )
        }

        composable(Routes.LOGIN) {
            val welcomeViewModel = get<AndroidLoginViewModel>()
            val state by welcomeViewModel.state.collectAsState()
            LoginScreen(
                state = state,
                navigator = navigator
            )
        }

    }
}