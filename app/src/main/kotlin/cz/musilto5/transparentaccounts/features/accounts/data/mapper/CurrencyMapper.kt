package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.common.domain.model.Currency

internal class CurrencyMapper {
    fun map(value: String?): Currency? = value?.let { Currency(it) }
}
