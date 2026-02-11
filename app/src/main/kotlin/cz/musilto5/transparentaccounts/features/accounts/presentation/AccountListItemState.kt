package cz.musilto5.transparentaccounts.features.accounts.presentation

import cz.musilto5.transparentaccounts.features.accounts.domain.model.Account

/**
 * Represents a single slot in the paged account list.
 * [Placeholder] when the item is not yet loaded (Paging 3); [Loaded] when the account is available.
 */
sealed class AccountListItemState {
    data object Placeholder : AccountListItemState()
    data class Loaded(val account: Account) : AccountListItemState()
}
