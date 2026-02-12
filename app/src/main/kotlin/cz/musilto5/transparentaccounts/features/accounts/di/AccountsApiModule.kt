package cz.musilto5.transparentaccounts.features.accounts.di

import android.util.Log
import cz.musilto5.transparentaccounts.BuildConfig
import cz.musilto5.transparentaccounts.features.accounts.data.api.DefaultApi
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger
import org.koin.dsl.module

private const val KTOR_LOG_TAG = "Ktor"

fun provideDefaultApi(): DefaultApi =
    DefaultApi(
        baseUrl = BuildConfig.TRANSPARENT_ACCOUNTS_BASE_URL,
        httpClientConfig = { config ->
            config.install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d(KTOR_LOG_TAG, message)
                    }
                }
                level = LogLevel.BODY
                sanitizeHeader { name: String ->
                    name == "Authorization" || name == "WEB-API-key"
                }
            }
        }
    ).apply {
        setApiKey(BuildConfig.API_KEY)
    }

val accountsApiModule = module {
    single<DefaultApi> { provideDefaultApi() }
}
