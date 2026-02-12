package cz.musilto5.transparentaccounts.features.accounts.di

import cz.musilto5.transparentaccounts.features.accounts.data.mapper.AccountDtoMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.AccountIdMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.BankAccountIdentifierMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.CurrencyMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.IbanMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.MoneyMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.PagingInfoMapper
import org.koin.dsl.module

val accountsMappersModule = module {

    single { AccountIdMapper() }
    single { BankAccountIdentifierMapper() }
    single { CurrencyMapper() }
    single { MoneyMapper(currencyMapper = get()) }
    single { IbanMapper() }
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

}
