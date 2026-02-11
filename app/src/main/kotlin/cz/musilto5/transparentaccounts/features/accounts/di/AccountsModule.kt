package cz.musilto5.transparentaccounts.features.accounts.di

import org.koin.dsl.module

val accountsModule = module {
    includes(
        accountsApiModule,
        accountsMappersModule,
        accountsDataModule,
        accountsPresentationModule
    )
}
