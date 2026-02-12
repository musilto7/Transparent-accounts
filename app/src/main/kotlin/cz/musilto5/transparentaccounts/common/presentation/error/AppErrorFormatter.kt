package cz.musilto5.transparentaccounts.common.presentation.error

import androidx.annotation.StringRes
import cz.musilto5.transparentaccounts.R
import cz.musilto5.transparentaccounts.common.domain.error.AppError

enum class AppErrorColorRole {
    ERROR,
    WARNING
}

private data class AppErrorPresentation(
    @StringRes val messageResId: Int,
    val colorRole: AppErrorColorRole
)

private object AppErrorPalette {
    fun map(error: AppError): AppErrorPresentation = when (error) {
        is AppError.NetworkError -> AppErrorPresentation(
            messageResId = R.string.error_network,
            colorRole = AppErrorColorRole.WARNING
        )
        is AppError.ApiError -> AppErrorPresentation(
            messageResId = R.string.error_api,
            colorRole = AppErrorColorRole.ERROR
        )
        is AppError.UnknownError -> AppErrorPresentation(
            messageResId = R.string.error_unknown,
            colorRole = AppErrorColorRole.ERROR
        )
    }
}

/**
 * Formats [AppError] for logging and provides string resource IDs for user-visible messages.
 * Use [messageResId] with [androidx.compose.ui.res.stringResource] for UI; use [formatForLog] for logs only.
 */
class AppErrorFormatter {

    @StringRes
    fun messageResId(error: AppError): Int = AppErrorPalette.map(error).messageResId

    fun colorRole(error: AppError): AppErrorColorRole = AppErrorPalette.map(error).colorRole

    fun formatForLog(error: AppError): String = when (error) {
        is AppError.NetworkError -> "NetworkError"
        is AppError.ApiError -> "ApiError(statusCode=${error.statusCode})"
        is AppError.UnknownError -> "UnknownError(causeMessage=${error.causeMessage ?: "null"})"
    }
}
