package org.furkan.kmmquickstart

import android.app.Application
import org.furkan.kmmquickstart.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KmmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KmmApplication)
            modules(appModule)
        }
    }
}