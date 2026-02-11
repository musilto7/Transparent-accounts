package cz.musilto5.transparentaccounts.common.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState

/**
 * Adds a single [LazyListScope] item for refresh [LoadState]: full-screen loading or error message.
 */
fun LazyListScope.refreshLoadStateItem(
    state: LoadState,
    errorPrefix: String = "Chyba: "
) {
    when (state) {
        is LoadState.Loading -> item {
            FullScreenLoading()
        }
        is LoadState.Error -> item {
            PagingErrorItem(
                message = state.error.message.orEmpty(),
                prefix = errorPrefix
            )
        }
        else -> {}
    }
}

/**
 * Adds a single [LazyListScope] item for append [LoadState]: footer loading or error message.
 */
fun LazyListScope.appendLoadStateItem(
    state: LoadState,
    errorPrefix: String = "Chyba načítání: "
) {
    when (state) {
        is LoadState.Loading -> item {
            AppendLoadingItem()
        }
        is LoadState.Error -> item {
            PagingErrorItem(
                message = state.error.message.orEmpty(),
                prefix = errorPrefix
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
    modifier: Modifier = Modifier
) {
    Text(
        text = "$prefix$message",
        color = MaterialTheme.colorScheme.error,
        modifier = modifier.padding(16.dp)
    )
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
