package cz.musilto5.transparentaccounts.features.accounts.presentation

import cz.musilto5.transparentaccounts.features.accounts.presentation.model.AccountViewObject

/**
 * Represents a single slot in the paged account list.
 * [Placeholder] when the item is not yet loaded (Paging 3); [Loaded] when the account is available.
 */
sealed class AccountsScreenItemState {
    data object Placeholder : AccountsScreenItemState()
    data class Loaded(val viewObject: AccountViewObject) : AccountsScreenItemState()
}
