package cz.musilto5.transparentaccounts.app.di

import cz.musilto5.transparentaccounts.common.domain.error.ExceptionToAppErrorMapper
import cz.musilto5.transparentaccounts.common.presentation.error.AppErrorFormatter
import cz.musilto5.transparentaccounts.features.accounts.di.accountsModule
import org.koin.dsl.module

val appModule = module {
    includes(accountsModule)
    single { ExceptionToAppErrorMapper() }
    single { AppErrorFormatter() }
}
