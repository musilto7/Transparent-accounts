package cz.musilto5.transparentaccounts.common.domain.error

/**
 * Typed representation of application errors.
 * Use [ExceptionToAppErrorMapper] to map from [Throwable], and [AppErrorFormatter] for display/logging.
 */
sealed class AppError {

    /** Network/connectivity failure (e.g. no connection, timeout). */
    data object NetworkError : AppError()

    /** API returned an error response. */
    data class ApiError(val statusCode: Int) : AppError()

    /** Unexpected error; [causeMessage] may be used for logging only, not for user display. */
    data class UnknownError(val causeMessage: String?) : AppError()
}
