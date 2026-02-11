package cz.musilto5.transparentaccounts.features.accounts.domain.model

import cz.musilto5.transparentaccounts.common.domain.model.BankAccountIdentifier
import cz.musilto5.transparentaccounts.common.domain.model.Money
import cz.musilto5.transparentaccounts.common.domain.model.Symbol
import kotlinx.datetime.Instant

/**
 * Domain model for a transaction.
 */
data class Transaction(
    val amount: Money?,
    val processingDate: Instant?,
    val description: String?,
    val sender: TransactionSender?,
    val variableSymbol: Symbol?,
    val constantSymbol: Symbol?,
    val specificSymbol: Symbol?
)

data class TransactionSender(
    val name: String?,
    val bankAccount: BankAccountIdentifier?
)
