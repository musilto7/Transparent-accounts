package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.common.domain.model.BankAccountIdentifier
import cz.musilto5.transparentaccounts.common.domain.model.AccountNumber
import cz.musilto5.transparentaccounts.common.domain.model.BankCode

internal class BankAccountIdentifierMapper {
    /**
     * Maps API account number and bank code to [BankAccountIdentifier].
     * If [accountNumber] contains a dash (e.g. "000000-0109213309"), the part before is stored as prefix, the part after as account number.
     */
    fun map(accountNumber: String?, bankCode: String?, prefix: String? = null): BankAccountIdentifier? {
        if (accountNumber == null || bankCode == null) return null
        val (parsedPrefix, parsedAccountNumber) = when (val dashIndex = accountNumber.indexOf('-')) {
            -1 -> null to accountNumber.trim()
            else -> {
                val pre = accountNumber.take(dashIndex).trim()
                val num = accountNumber.substring(dashIndex + 1).trim()
                if (num.isBlank()) return null
                (pre.ifBlank { null }) to num
            }
        }
        return BankAccountIdentifier(
            accountNumber = AccountNumber(parsedAccountNumber),
            bankCode = BankCode(bankCode),
            prefix = prefix ?: parsedPrefix
        )
    }
}
