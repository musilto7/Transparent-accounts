package cz.musilto5.transparentaccounts.features.accounts.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import cz.musilto5.transparentaccounts.common.presentation.appendLoadStateItem
import cz.musilto5.transparentaccounts.common.presentation.refreshLoadStateItem
import cz.musilto5.transparentaccounts.common.presentation.theme.LocalAccountListSpacing
import cz.musilto5.transparentaccounts.common.presentation.theme.TransparentAccountsTheme
import cz.musilto5.transparentaccounts.features.accounts.presentation.model.AccountViewObject
import cz.musilto5.transparentaccounts.features.accounts.presentation.viewmodel.AccountsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountsScreen(
    viewModel: AccountsViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val accounts = viewModel.accountsFlow.collectAsLazyPagingItems()
    AccountsList(
        accounts = accounts,
        modifier = modifier
    )
}

@Composable
private fun AccountsList(
    accounts: LazyPagingItems<AccountViewObject>,
    modifier: Modifier = Modifier
) {
    val spacing = LocalAccountListSpacing.current
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(spacing.contentPadding),
        verticalArrangement = Arrangement.spacedBy(spacing.itemSpacing)
    ) {
        accountsListItems(accounts)
        refreshLoadStateItem(accounts.loadState.refresh)
        appendLoadStateItem(accounts.loadState.append)
    }
}

private fun LazyListScope.accountsListItems(
    accounts: LazyPagingItems<AccountViewObject>
) {
    items(
        count = accounts.itemCount,
        key = { index: Int -> index }
    ) { index: Int ->
        val state = accounts[index]?.let { viewObject ->
            AccountListItemState.Loaded(viewObject)
        } ?: AccountListItemState.Placeholder
        AccountListItem(state = state)
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountsListContentPreview() {
    TransparentAccountsTheme {
        val spacing = LocalAccountListSpacing.current
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(spacing.contentPadding),
            verticalArrangement = Arrangement.spacedBy(spacing.itemSpacing)
        ) {
            items(
                listOf(
                    AccountViewObject("Current account", "123456/0800", "CZK"),
                    AccountViewObject("Savings", "987654/0600", "EUR")
                )
            ) { viewObject ->
                AccountListItem(state = AccountListItemState.Loaded(viewObject))
            }
        }
    }
}
