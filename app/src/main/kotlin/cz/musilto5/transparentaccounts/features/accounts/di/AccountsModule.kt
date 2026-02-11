package cz.musilto5.transparentaccounts.features.accounts.di

import cz.musilto5.transparentaccounts.BuildConfig
import cz.musilto5.transparentaccounts.features.accounts.data.datasource.TransparentAccountsDataSource
import cz.musilto5.transparentaccounts.features.accounts.data.datasource.TransparentAccountsDataSourceImpl
import cz.musilto5.transparentaccounts.features.accounts.data.repository.TransparentAccountsRepositoryImpl
import cz.musilto5.transparentaccounts.features.accounts.domain.repository.TransparentAccountsRepository
import cz.musilto5.transparentaccounts.features.accounts.presentation.AccountsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountsModule = module {

    single<TransparentAccountsDataSource> {
        TransparentAccountsDataSourceImpl(
            apiKey = BuildConfig.API_KEY
        )
    }

    single<TransparentAccountsRepository> {
        TransparentAccountsRepositoryImpl(
            dataSource = get()
        )
    }

    viewModel { AccountsViewModel(repository = get()) }
}
