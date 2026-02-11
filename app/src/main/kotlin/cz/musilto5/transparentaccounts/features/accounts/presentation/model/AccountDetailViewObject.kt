package cz.musilto5.transparentaccounts.features.accounts.presentation.model

/**
 * View object for account detail screen. All values are formatted for display.
 */
data class AccountDetailViewObject(
    val name: String,
    val bankAccount: String,
    val currency: String,
    val balance: String,
    val iban: String,
    val description: String,
    val note: String
)
