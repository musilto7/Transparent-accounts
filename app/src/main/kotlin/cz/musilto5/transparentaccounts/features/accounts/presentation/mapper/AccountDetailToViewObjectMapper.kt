package cz.musilto5.transparentaccounts.features.accounts.presentation.mapper

import cz.musilto5.transparentaccounts.common.domain.model.Money
import cz.musilto5.transparentaccounts.common.presentation.PLACEHOLDER
import cz.musilto5.transparentaccounts.common.presentation.orPlaceholder
import cz.musilto5.transparentaccounts.features.accounts.domain.model.AccountDetail
import cz.musilto5.transparentaccounts.features.accounts.presentation.model.AccountDetailViewObject
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class AccountDetailToViewObjectMapper {

    private val decimalFormat = DecimalFormat(
        "#,##0.00",
        DecimalFormatSymbols(Locale.getDefault())
    )

    fun map(detail: AccountDetail): AccountDetailViewObject = AccountDetailViewObject(
        name = detail.name.orPlaceholder(),
        bankAccount = detail.bankAccount?.toString().orPlaceholder(),
        currency = detail.balance?.currency?.toString().orPlaceholder(),
        balance = formatBalance(detail.balance),
        iban = detail.iban?.value.orPlaceholder(),
        description = detail.description.orPlaceholder(),
        note = detail.note.orPlaceholder()
    )

    private fun formatBalance(balance: Money?): String =
        if (balance != null) {
            val amountStr = decimalFormat.format(balance.amount)
            val currencyStr = balance.currency.toString()
            if (currencyStr.isNotBlank()) "$amountStr $currencyStr" else amountStr
        } else PLACEHOLDER
}
