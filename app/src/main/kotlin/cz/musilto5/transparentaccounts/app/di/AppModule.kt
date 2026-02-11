package cz.musilto5.transparentaccounts.app.di

import cz.musilto5.transparentaccounts.features.accounts.di.accountsModule
import org.koin.dsl.module

val appModule = module {
    includes(accountsModule)
}
