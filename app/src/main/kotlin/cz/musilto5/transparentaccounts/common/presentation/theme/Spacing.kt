package cz.musilto5.transparentaccounts.common.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AccountListSpacing(
    val contentPadding: Dp = 16.dp,
    val itemSpacing: Dp = 8.dp
)

val LocalAccountListSpacing = compositionLocalOf { AccountListSpacing() }
