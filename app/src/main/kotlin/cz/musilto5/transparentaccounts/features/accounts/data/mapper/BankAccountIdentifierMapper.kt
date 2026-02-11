package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.common.domain.model.BankAccountIdentifier
import cz.musilto5.transparentaccounts.common.domain.model.AccountNumber
import cz.musilto5.transparentaccounts.common.domain.model.BankCode

class BankAccountIdentifierMapper {
    fun map(accountNumber: String?, bankCode: String?, prefix: String? = null): BankAccountIdentifier? =
        if (accountNumber == null || bankCode == null) null
        else BankAccountIdentifier(
            accountNumber = AccountNumber(accountNumber),
            bankCode = BankCode(bankCode),
            prefix = prefix
        )
}
