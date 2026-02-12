package cz.musilto5.transparentaccounts.features.accounts.di

import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.features.accounts.presentation.viewmodel.AccountDetailViewModel
import cz.musilto5.transparentaccounts.features.accounts.presentation.viewmodel.AccountsViewModel
import cz.musilto5.transparentaccounts.features.accounts.presentation.mapper.AccountDetailToViewObjectMapper
import cz.musilto5.transparentaccounts.features.accounts.presentation.mapper.AccountToViewObjectMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountsPresentationModule = module {
    single { AccountToViewObjectMapper() }
    single { AccountDetailToViewObjectMapper() }
    viewModel { AccountsViewModel(repository = get(), accountToViewObjectMapper = get()) }
    viewModel { (accountId: AccountId) ->
        AccountDetailViewModel(
            accountId = accountId,
            repository = get(),
            accountDetailToViewObjectMapper = get(),
            exceptionToAppErrorMapper = get()
        )
    }
}
