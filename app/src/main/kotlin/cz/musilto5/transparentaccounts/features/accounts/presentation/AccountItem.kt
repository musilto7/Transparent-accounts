package cz.musilto5.transparentaccounts.features.accounts.presentation

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountReferenceDto

/**
 * Composable for a single account row in a list. No-op when [account] is null (e.g. paging placeholder).
 */
@Composable
fun AccountListItem(
    account: AccountReferenceDto?,
    modifier: Modifier = Modifier
) {
    if (account != null) {
        AccountItem(account = account, modifier = modifier)
    }
}

@Composable
fun AccountItem(
    account: AccountReferenceDto,
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
            text = "${account.accountNumber ?: ""} / ${account.bankCode ?: ""}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
        Text(
            text = account.currency ?: "",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}
