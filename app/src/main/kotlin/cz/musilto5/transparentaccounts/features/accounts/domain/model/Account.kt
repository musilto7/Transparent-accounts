package cz.musilto5.transparentaccounts.features.accounts.domain.model

import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.common.domain.model.BankAccountIdentifier
import cz.musilto5.transparentaccounts.common.domain.model.Currency

/**
 * Domain model for a transparent account (list item).
 */
data class Account(
    val id: AccountId?,
    val bankAccount: BankAccountIdentifier?,
    val name: String?,
    val currency: Currency?
)
