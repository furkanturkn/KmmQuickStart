package org.furkan.kmmquickstart.core.presentation.navigation

interface CoreNavigator {
    fun navigate(event: NavigationEvent)

    companion object {
        val mock = object : CoreNavigator {
            override fun navigate(event: NavigationEvent) {

            }
        }
    }
}
