package di

import core.data.getHttpClientFactory
import core.domain.HttpApiRequest
import core.domain.KtorApiRequestImpl
import core.domain.util.KmmContext
import core.domain.util.preference.KmmPreference
import login.domain.use_case.ValidatePhoneNumberUseCase


object AppModule {
    private val okHttpClient: HttpApiRequest by lazy {
        KtorApiRequestImpl(
            getHttpClientFactory().create()
        )
    }

    val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase by lazy {
        ValidatePhoneNumberUseCase()
    }

    val sharedPref: KmmPreference by lazy {
        KmmPreference(KmmContext())
    }
}
