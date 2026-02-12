package cz.musilto5.transparentaccounts.features.accounts.di

import cz.musilto5.transparentaccounts.features.accounts.data.datasource.TransparentAccountsDataSource
import cz.musilto5.transparentaccounts.features.accounts.data.datasource.TransparentAccountsDataSourceImpl
import cz.musilto5.transparentaccounts.features.accounts.data.repository.TransparentAccountsRepositoryImpl
import cz.musilto5.transparentaccounts.features.accounts.domain.repository.TransparentAccountsRepository
import org.koin.dsl.module

val accountsDataModule = module {

    single<TransparentAccountsDataSource> {
        TransparentAccountsDataSourceImpl(api = get())
    }

    single<TransparentAccountsRepository> {
        TransparentAccountsRepositoryImpl(
            dataSource = get(),
            accountDtoMapper = get()
        )
    }
}
