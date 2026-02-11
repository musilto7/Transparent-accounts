package cz.musilto5.transparentaccounts.features.accounts.di

import cz.musilto5.transparentaccounts.features.accounts.presentation.AccountsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountsPresentationModule = module {
    viewModel { AccountsViewModel(repository = get()) }
}
