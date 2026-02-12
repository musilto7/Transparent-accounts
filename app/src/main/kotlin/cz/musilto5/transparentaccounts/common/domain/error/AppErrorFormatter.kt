package cz.musilto5.transparentaccounts.common.domain.error

import androidx.annotation.StringRes
import cz.musilto5.transparentaccounts.R

/**
 * Formats [AppError] for logging and provides string resource IDs for user-visible messages.
 * Use [messageResId] with [androidx.compose.ui.res.stringResource] for UI; use [formatForLog] for logs only.
 */
class AppErrorFormatter {

    /**
     * String resource ID for the user-facing message for this error.
     * Use with stringResource(messageResId(error)) in Composables.
     */
    @StringRes
    fun messageResId(error: AppError): Int = when (error) {
        is AppError.NetworkError -> R.string.error_network
        is AppError.ApiError -> R.string.error_api
        is AppError.UnknownError -> R.string.error_unknown
    }

    /**
     * Returns a string suitable for logging or debugging.
     * Do not use for user-visible UI; use [messageResId] and string resources instead.
     */
    fun formatForLog(error: AppError): String = when (error) {
        is AppError.NetworkError -> "NetworkError"
        is AppError.ApiError -> "ApiError(statusCode=${error.statusCode})"
        is AppError.UnknownError -> "UnknownError(causeMessage=${error.causeMessage ?: "null"})"
    }
}
