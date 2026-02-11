package cz.musilto5.transparentaccounts.di

import cz.musilto5.transparentaccounts.BuildConfig
import cz.musilto5.transparentaccounts.data.datasource.TransparentAccountsDataSource
import cz.musilto5.transparentaccounts.data.datasource.TransparentAccountsDataSourceImpl
import cz.musilto5.transparentaccounts.data.repository.TransparentAccountsRepositoryImpl
import cz.musilto5.transparentaccounts.domain.repository.TransparentAccountsRepository
import cz.musilto5.transparentaccounts.presentation.accounts.AccountsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

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
