package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.common.domain.model.Currency
import cz.musilto5.transparentaccounts.common.domain.model.Money

internal class MoneyMapper(
    private val currencyMapper: CurrencyMapper
) {
    fun map(currency: String?, amount: Double?): Money? = run {
        val c = currencyMapper.map(currency) ?: return@run null
        val a = amount ?: return@run null
        Money(c, a)
    }
}
