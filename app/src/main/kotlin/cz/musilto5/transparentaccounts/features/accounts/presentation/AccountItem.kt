package cz.musilto5.transparentaccounts.features.accounts.presentation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.musilto5.transparentaccounts.features.accounts.domain.model.Account

/**
 * Composable for a single account row in a paged list.
 * Shows a placeholder (skeleton) for [AccountListItemState.Placeholder], the account card for [AccountListItemState.Loaded].
 */
@Composable
fun AccountListItem(
    state: AccountListItemState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is AccountListItemState.Placeholder -> AccountListItemPlaceholder(modifier = modifier)
        is AccountListItemState.Loaded -> AccountItem(account = state.account, modifier = modifier)
    }
}

@Composable
private fun AccountListItemPlaceholder(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(72.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {}
}

@Composable
fun AccountItem(
    account: Account,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Text(
            text = account.name ?: "—",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(12.dp)
        )
        Text(
            text = account.bankAccount?.toString() ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
        Text(
            text = account.currency?.toString() ?: "",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}
