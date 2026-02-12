package cz.musilto5.transparentaccounts.features.accounts.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.musilto5.transparentaccounts.R
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.common.presentation.error.AppErrorFormatter
import cz.musilto5.transparentaccounts.common.domain.error.ExceptionToAppErrorMapper
import cz.musilto5.transparentaccounts.common.presentation.ErrorScreen
import cz.musilto5.transparentaccounts.common.presentation.FullScreenLoading
import cz.musilto5.transparentaccounts.common.presentation.PagingErrorItem
import cz.musilto5.transparentaccounts.common.presentation.appendLoadStateItem
import cz.musilto5.transparentaccounts.common.presentation.theme.LocalAccountListSpacing
import cz.musilto5.transparentaccounts.common.presentation.theme.TransparentAccountsTheme
import cz.musilto5.transparentaccounts.features.accounts.presentation.model.AccountViewObject
import cz.musilto5.transparentaccounts.features.accounts.presentation.viewmodel.AccountsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun AccountsScreen(
    viewModel: AccountsViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    onAccountClick: (AccountId) -> Unit
) {
    val accounts = viewModel.accountsFlow.collectAsLazyPagingItems()
    AccountsList(
        accounts = accounts,
        modifier = modifier,
        onAccountClick = onAccountClick
    )
}

@Composable
private fun AccountsList(
    accounts: LazyPagingItems<AccountViewObject>,
    modifier: Modifier = Modifier,
    onAccountClick: (AccountId) -> Unit,
    exceptionToAppErrorMapper: ExceptionToAppErrorMapper = koinInject(),
    appErrorFormatter: AppErrorFormatter = koinInject()
) {
    val spacing = LocalAccountListSpacing.current
    val errorPrefix = stringResource(R.string.error_prefix)
    val errorLoadingPrefix = stringResource(R.string.error_loading_prefix)
    val refreshState = accounts.loadState.refresh
    val appendState = accounts.loadState.append
    val showErrorScreen = refreshState is LoadState.Error && accounts.itemCount == 0

    val refreshErrorResId = remember(refreshState) {
        when (refreshState) {
            is LoadState.Error -> appErrorFormatter.messageResId(exceptionToAppErrorMapper.map(refreshState.error))
            else -> R.string.error_load_failed
        }
    }
    val appendErrorResId = remember(appendState) {
        when (appendState) {
            is LoadState.Error -> appErrorFormatter.messageResId(exceptionToAppErrorMapper.map(appendState.error))
            else -> R.string.error_load_failed
        }
    }
    val refreshErrorMessage = stringResource(refreshErrorResId)
    val appendErrorMessage = stringResource(appendErrorResId)

    if (showErrorScreen) {
        ErrorScreen(
            message = refreshErrorMessage,
            onReloadClick = { accounts.retry() },
            modifier = modifier.fillMaxSize()
        )
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(spacing.contentPadding),
            verticalArrangement = Arrangement.spacedBy(spacing.itemSpacing)
        ) {
            accountsListItems(accounts, onAccountClick = onAccountClick)
            refreshLoadStateWithSkeletons(
                refreshState = refreshState,
                itemCount = accounts.itemCount,
                skeletonCount = 8,
                errorPrefix = errorPrefix,
                errorMessage = refreshErrorMessage,
                onRetry = { accounts.retry() }
            )
            appendLoadStateItem(
                appendState,
                errorPrefix = errorLoadingPrefix,
                errorMessage = appendErrorMessage,
                onRetry = { accounts.retry() }
            )
        }
    }
}

private fun LazyListScope.accountsListItems(
    accounts: LazyPagingItems<AccountViewObject>,
    onAccountClick: (AccountId) -> Unit
) {
    items(count = accounts.itemCount) { index: Int ->
        val state = accounts[index]?.let { viewObject ->
            AccountsScreenItemState.Loaded(viewObject)
        } ?: AccountsScreenItemState.Placeholder
        AccountListItem(state = state, onAccountClick = onAccountClick)
    }
}

private fun LazyListScope.refreshLoadStateWithSkeletons(
    refreshState: LoadState,
    itemCount: Int,
    skeletonCount: Int,
    errorPrefix: String,
    errorMessage: String,
    onRetry: () -> Unit
) {
    when (refreshState) {
        is LoadState.Loading -> if (itemCount == 0) {
            items(skeletonCount, key = { "skeleton_$it" }) {
                AccountListItem(state = AccountsScreenItemState.Placeholder)
            }
        } else {
            item(key = "loading") { FullScreenLoading() }
        }
        is LoadState.Error -> if (itemCount > 0) {
            item(key = "refresh_error") {
                PagingErrorItem(
                    message = errorMessage,
                    prefix = errorPrefix,
                    onRetry = onRetry
                )
            }
        }
        else -> {}
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountsListContentPreview() {
    TransparentAccountsTheme {
        val spacing = LocalAccountListSpacing.current
        val previewItems = listOf(
            AccountViewObject(
                id = AccountId("preview-1"),
                name = stringResource(R.string.preview_account_current),
                bankAccount = stringResource(R.string.preview_account_bank),
                currency = stringResource(R.string.preview_currency_czk)
            ),
            AccountViewObject(
                id = AccountId("preview-2"),
                name = stringResource(R.string.preview_account_savings),
                bankAccount = stringResource(R.string.preview_account_bank_2),
                currency = stringResource(R.string.preview_currency_eur)
            )
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(spacing.contentPadding),
            verticalArrangement = Arrangement.spacedBy(spacing.itemSpacing)
        ) {
            items(previewItems, key = { it.id }) { viewObject ->
                AccountListItem(state = AccountsScreenItemState.Loaded(viewObject))
            }
        }
    }
}
