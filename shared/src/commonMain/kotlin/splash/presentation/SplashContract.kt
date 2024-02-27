package splash.presentation

interface SplashContract {

    data class State(
        val currentRoute: String? = null
    )
    sealed class Event {
        object NavigateToLoginScreen : Event()
        object NavigateToHomeScreen : Event()
    }

}
