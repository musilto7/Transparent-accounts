package cz.musilto5.transparentaccounts.common.domain.error

/**
 * Marker for exceptions that represent an API error with an HTTP status code.
 * Implemented by data-layer API exceptions so [ExceptionToAppErrorMapper] can map them to [AppError.ApiError].
 */
interface ApiExceptionWithStatus {
    val statusCode: Int
}
