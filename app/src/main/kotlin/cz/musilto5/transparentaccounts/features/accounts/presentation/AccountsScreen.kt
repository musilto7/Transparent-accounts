package cz.musilto5.transparentaccounts.features.accounts.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import cz.musilto5.transparentaccounts.common.presentation.appendLoadStateItem
import cz.musilto5.transparentaccounts.common.presentation.refreshLoadStateItem
import cz.musilto5.transparentaccounts.common.presentation.theme.LocalAccountListSpacing
import cz.musilto5.transparentaccounts.features.accounts.domain.model.Account
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
    accounts: LazyPagingItems<Account>,
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

private fun LazyListScope.accountsListItems(accounts: LazyPagingItems<Account>) {
    items(
        count = accounts.itemCount,
        key = { index: Int -> accounts[index]?.id?.value ?: index }
    ) { index: Int ->
        val state = accounts[index]?.let { AccountListItemState.Loaded(it) }
            ?: AccountListItemState.Placeholder
        AccountListItem(state = state)
    }
}
