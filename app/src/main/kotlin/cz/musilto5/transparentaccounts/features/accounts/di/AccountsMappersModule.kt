package cz.musilto5.transparentaccounts.features.accounts.di

import cz.musilto5.transparentaccounts.features.accounts.data.mapper.AccountDtoMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.AccountIdMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.BankAccountIdentifierMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.CurrencyMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.IbanMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.MoneyMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.PagingInfoMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.SymbolMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.TransactionDtoMapper
import org.koin.dsl.module

val accountsMappersModule = module {

    single { AccountIdMapper() }
    single { BankAccountIdentifierMapper() }
    single { CurrencyMapper() }
    single { MoneyMapper(currencyMapper = get()) }
    single { IbanMapper() }
    single { SymbolMapper() }
    single { PagingInfoMapper() }

    single {
        AccountDtoMapper(
            accountIdMapper = get(),
            bankAccountIdentifierMapper = get(),
            currencyMapper = get(),
            moneyMapper = get(),
            ibanMapper = get(),
            pagingInfoMapper = get()
        )
    }

    single {
        TransactionDtoMapper(
            bankAccountIdentifierMapper = get(),
            moneyMapper = get(),
            symbolMapper = get(),
            pagingInfoMapper = get()
        )
    }
}
