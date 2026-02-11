package cz.musilto5.transparentaccounts.features.accounts.presentation.model

/**
 * View object for a single account list item. All values are formatted for display.
 */
data class AccountViewObject(
    val name: String,
    val bankAccount: String,
    val currency: String
)
