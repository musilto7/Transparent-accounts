package cz.musilto5.transparentaccounts.features.accounts.di

import cz.musilto5.transparentaccounts.BuildConfig
import cz.musilto5.transparentaccounts.features.accounts.data.api.DefaultApi
import org.koin.dsl.module

fun provideDefaultApi(): DefaultApi =
    DefaultApi(baseUrl = BuildConfig.TRANSPARENT_ACCOUNTS_BASE_URL).apply {
        setApiKey(BuildConfig.API_KEY)
    }

val accountsApiModule = module {
    single<DefaultApi> { provideDefaultApi() }
}
