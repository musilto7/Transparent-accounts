package cz.musilto5.transparentaccounts.common.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.musilto5.transparentaccounts.R
import androidx.paging.LoadState
import cz.musilto5.transparentaccounts.common.presentation.theme.TransparentAccountsTheme

/**
 * Adds a single [LazyListScope] item for refresh [LoadState]: full-screen loading or error message.
 * @param errorMessage User-visible error text (e.g. from [stringResource]); raw exception message is not shown.
 * @param onRetry Called when the user taps retry; pass to allow recovery from error.
 */
fun LazyListScope.refreshLoadStateItem(
    state: LoadState,
    errorPrefix: String,
    errorMessage: String,
    onRetry: (() -> Unit)? = null
) {
    when (state) {
        is LoadState.Loading -> item {
            FullScreenLoading()
        }
        is LoadState.Error -> item {
            PagingErrorItem(
                message = errorMessage,
                prefix = errorPrefix,
                onRetry = onRetry
            )
        }
        else -> {}
    }
}

/**
 * Adds a single [LazyListScope] item for append [LoadState]: footer loading or error message.
 * @param errorMessage User-visible error text (e.g. from [stringResource]); raw exception message is not shown.
 * @param onRetry Called when the user taps retry; pass to allow recovery from error.
 */
fun LazyListScope.appendLoadStateItem(
    state: LoadState,
    errorPrefix: String,
    errorMessage: String,
    onRetry: (() -> Unit)? = null
) {
    when (state) {
        is LoadState.Loading -> item {
            AppendLoadingItem()
        }
        is LoadState.Error -> item {
            PagingErrorItem(
                message = errorMessage,
                prefix = errorPrefix,
                onRetry = onRetry
            )
        }
        else -> {}
    }
}

@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun PagingErrorItem(
    message: String,
    prefix: String = "",
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "$prefix$message",
            color = MaterialTheme.colorScheme.error
        )
        if (onRetry != null) {
            Button(onClick = onRetry) {
                Text(stringResource(R.string.button_reload))
            }
        }
    }
}

@Composable
fun AppendLoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun FullScreenLoadingPreview() {
    TransparentAccountsTheme {
        FullScreenLoading()
    }
}

@Preview(showBackground = true)
@Composable
private fun PagingErrorItemPreview() {
    TransparentAccountsTheme {
        PagingErrorItem(message = stringResource(R.string.preview_error_message), prefix = stringResource(R.string.error_prefix))
    }
}

@Preview(showBackground = true)
@Composable
private fun AppendLoadingItemPreview() {
    TransparentAccountsTheme {
        AppendLoadingItem()
    }
}
