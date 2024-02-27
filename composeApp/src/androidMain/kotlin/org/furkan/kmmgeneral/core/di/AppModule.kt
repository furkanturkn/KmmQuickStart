package org.furkan.kmmquickstart.core.di

import core.data.getHttpClientFactory
import core.domain.HttpApiRequest
import core.domain.KtorApiRequestImpl
import core.domain.util.preference.KmmPreference
import login.domain.use_case.ValidatePhoneNumberUseCase
import org.furkan.kmmquickstart.login.AndroidLoginViewModel
import org.furkan.kmmquickstart.splash.AndroidSplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single {
        getHttpClientFactory().create()
    }

    single<HttpApiRequest> {
        KtorApiRequestImpl(get())
    }

    single {
        ValidatePhoneNumberUseCase()
    }

    single {
        KmmPreference(androidApplication())
    }

    viewModel { AndroidLoginViewModel(get()) }

    viewModel { AndroidSplashViewModel(get()) }

}