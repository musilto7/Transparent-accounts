package cz.musilto5.transparentaccounts.features.accounts.domain.model

import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.common.domain.model.BankAccountIdentifier
import cz.musilto5.transparentaccounts.common.domain.model.Iban
import cz.musilto5.transparentaccounts.common.domain.model.Money

/**
 * Domain model for full account detail.
 */
data class AccountDetail(
    val id: AccountId?,
    val bankAccount: BankAccountIdentifier?,
    val name: String?,
    val balance: Money?,
    val iban: Iban?,
    val description: String?,
    val note: String?
)
