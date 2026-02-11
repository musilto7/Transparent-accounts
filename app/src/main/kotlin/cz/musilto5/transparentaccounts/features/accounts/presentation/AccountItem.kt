package cz.musilto5.transparentaccounts.features.accounts.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.musilto5.transparentaccounts.common.presentation.theme.LocalAccountListSpacing
import cz.musilto5.transparentaccounts.common.presentation.theme.TransparentAccountsTheme
import cz.musilto5.transparentaccounts.features.accounts.presentation.model.AccountViewObject

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
        is AccountListItemState.Loaded -> AccountItem(viewObject = state.viewObject, modifier = modifier)
    }
}

@Composable
private fun AccountListItemPlaceholder(modifier: Modifier = Modifier) {
    val spacing = LocalAccountListSpacing.current
    val shape = MaterialTheme.shapes.medium
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(spacing.placeholderHeight)
            .clip(shape),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {}
}

@Composable
fun AccountItem(
    viewObject: AccountViewObject,
    modifier: Modifier = Modifier
) {
    val spacing = LocalAccountListSpacing.current
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = spacing.tileElevation)
    ) {
        Column(
            modifier = Modifier.padding(spacing.tilePadding)
        ) {
            Text(
                text = viewObject.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = viewObject.bankAccount,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = viewObject.currency,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountListItemPlaceholderPreview() {
    TransparentAccountsTheme {
        AccountListItem(state = AccountListItemState.Placeholder)
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountListItemLoadedPreview() {
    TransparentAccountsTheme {
        AccountListItem(
            state = AccountListItemState.Loaded(
                AccountViewObject(
                    name = "Sample Account",
                    bankAccount = "123456/0800",
                    currency = "CZK"
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountItemPreview() {
    TransparentAccountsTheme {
        AccountItem(
            viewObject = AccountViewObject(
                name = "Sample Account",
                bankAccount = "123456/0800",
                currency = "CZK"
            )
        )
    }
}
