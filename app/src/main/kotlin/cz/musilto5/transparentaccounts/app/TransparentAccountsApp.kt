package cz.musilto5.transparentaccounts.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import cz.musilto5.transparentaccounts.app.navigation.AccountDetail
import cz.musilto5.transparentaccounts.app.navigation.Accounts
import cz.musilto5.transparentaccounts.app.navigation.accountDetailTransitionMetadata
import cz.musilto5.transparentaccounts.features.accounts.presentation.AccountDetailScreen
import cz.musilto5.transparentaccounts.features.accounts.presentation.AccountsScreen

@Composable
fun TransparentAccountsApp() {
    val backStack = rememberNavBackStack(Accounts)
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            modifier = Modifier.padding(innerPadding),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Accounts> {
                    AccountsScreen(
                        onAccountClick = { accountId ->
                            backStack.add(AccountDetail(accountId))
                        }
                    )
                }
                entry<AccountDetail>(
                    metadata = accountDetailTransitionMetadata()
                ) { key ->
                    AccountDetailScreen(
                        navKey = key,
                        onBackClick = { backStack.removeLastOrNull() }
                    )
                }
            }
        )
    }
}
