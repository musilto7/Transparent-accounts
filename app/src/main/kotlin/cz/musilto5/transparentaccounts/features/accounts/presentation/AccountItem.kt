package cz.musilto5.transparentaccounts.features.accounts.presentation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.musilto5.transparentaccounts.R
import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.common.presentation.drawShimmer
import cz.musilto5.transparentaccounts.common.presentation.theme.LocalAccountListSpacing
import cz.musilto5.transparentaccounts.common.presentation.theme.TransparentAccountsTheme
import cz.musilto5.transparentaccounts.features.accounts.presentation.model.AccountViewObject

/**
 * Composable for a single account row in a paged list.
 * Shows a placeholder (skeleton) for [AccountsScreenItemState.Placeholder], the account card for [AccountsScreenItemState.Loaded].
 */
@Composable
fun AccountListItem(
    state: AccountsScreenItemState,
    modifier: Modifier = Modifier,
    onAccountClick: ((AccountId) -> Unit)? = null
) {
    when (state) {
        is AccountsScreenItemState.Placeholder -> AccountListItemPlaceholder(modifier = modifier)
        is AccountsScreenItemState.Loaded -> {
            val canNavigate = onAccountClick != null
            AccountItem(
                viewObject = state.viewObject,
                modifier = modifier,
                onClick = if (canNavigate) {
                    { onAccountClick?.invoke(state.viewObject.id) }
                } else null
            )
        }
    }
}

@Composable
private fun AccountListItemPlaceholder(modifier: Modifier = Modifier) {
    val spacing = LocalAccountListSpacing.current
    val shape = MaterialTheme.shapes.medium
    val baseColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
    val highlightColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f)
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1_200),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_offset"
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(spacing.placeholderHeight)
            .clip(shape),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = baseColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
                .drawShimmer(
                    baseColor = baseColor,
                    highlightColor = highlightColor,
                    shimmerOffset = shimmerOffset
                )
        )
    }
}

@Composable
fun AccountItem(
    viewObject: AccountViewObject,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    val spacing = LocalAccountListSpacing.current
    val cardModifier = if (onClick != null) {
        modifier.fillMaxWidth().clickable(onClick = onClick)
    } else {
        modifier.fillMaxWidth()
    }
    Card(
        modifier = cardModifier,
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
        AccountListItem(state = AccountsScreenItemState.Placeholder)
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountListItemLoadedPreview() {
    TransparentAccountsTheme {
        AccountListItem(
            state = AccountsScreenItemState.Loaded(
                AccountViewObject(
                    id = AccountId("preview"),
                    name = stringResource(R.string.preview_account_name),
                    bankAccount = stringResource(R.string.preview_account_bank),
                    currency = stringResource(R.string.preview_currency_czk)
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
                id = AccountId("preview"),
                name = stringResource(R.string.preview_account_name),
                bankAccount = stringResource(R.string.preview_account_bank),
                currency = stringResource(R.string.preview_currency_czk)
            )
        )
    }
}
