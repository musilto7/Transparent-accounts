package cz.musilto5.transparentaccounts.features.accounts.presentation.model

import cz.musilto5.transparentaccounts.common.domain.model.AccountId

/**
 * View object for a single account list item. All values are formatted for display.
 * [id] is a stable identifier for the account (for navigation and API calls).
 */
data class AccountViewObject(
    val id: AccountId,
    val name: String,
    val bankAccount: String,
    val currency: String
)
