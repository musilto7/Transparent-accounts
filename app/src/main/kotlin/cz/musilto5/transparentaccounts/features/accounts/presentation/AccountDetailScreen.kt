package cz.musilto5.transparentaccounts.features.accounts.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.musilto5.transparentaccounts.R
import cz.musilto5.transparentaccounts.app.navigation.AccountDetail
import cz.musilto5.transparentaccounts.common.domain.error.AppErrorFormatter
import cz.musilto5.transparentaccounts.common.presentation.ErrorScreen
import cz.musilto5.transparentaccounts.common.presentation.theme.LocalAccountListSpacing
import cz.musilto5.transparentaccounts.common.presentation.theme.TransparentAccountsTheme
import cz.musilto5.transparentaccounts.features.accounts.presentation.model.AccountDetailViewObject
import cz.musilto5.transparentaccounts.features.accounts.presentation.viewmodel.AccountDetailScreenState
import cz.musilto5.transparentaccounts.features.accounts.presentation.viewmodel.AccountDetailViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailScreen(
    navKey: AccountDetail,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountDetailViewModel = koinViewModel(parameters = { parametersOf(navKey.accountId) }),
    appErrorFormatter: AppErrorFormatter = koinInject()
) {
    val state by viewModel.state.collectAsState()
    val spacing = LocalAccountListSpacing.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.detail_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        AnimatedContent(
            targetState = state,
            modifier = Modifier.fillMaxSize(),
            transitionSpec = {
                fadeIn().togetherWith(fadeOut())
            },
            label = "AccountDetailScreenState"
        ) { s ->
            when (s) {
                is AccountDetailScreenState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is AccountDetailScreenState.Error -> {
                    val errorMessage = stringResource(appErrorFormatter.messageResId(s.error))
                    ErrorScreen(
                        message = errorMessage,
                        onReloadClick = { viewModel.retry() },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
                is AccountDetailScreenState.Success -> {
                    AccountDetailContent(
                        detail = s.detail,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(horizontal = spacing.contentPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun AccountDetailContent(
    detail: AccountDetailViewObject,
    modifier: Modifier = Modifier
) {
    val spacing = LocalAccountListSpacing.current
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(vertical = spacing.contentPadding)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
            )
        ) {
            Column(modifier = Modifier.padding(spacing.tilePadding)) {
                DetailRow(stringResource(R.string.detail_name), detail.name)
                DetailRow(stringResource(R.string.detail_bank_account), detail.bankAccount)
                DetailRow(stringResource(R.string.detail_currency), detail.currency)
                DetailRow(stringResource(R.string.detail_balance), detail.balance)
                DetailRow(stringResource(R.string.detail_iban), detail.iban)
                DetailRow(stringResource(R.string.detail_description), detail.description)
                DetailRow(stringResource(R.string.detail_note), detail.note)
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.outline
    )
    Text(
        text = value,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
    Spacer(modifier = Modifier.height(12.dp))
}
