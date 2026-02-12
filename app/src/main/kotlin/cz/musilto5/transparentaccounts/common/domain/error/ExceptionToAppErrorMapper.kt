package cz.musilto5.transparentaccounts.common.domain.error

import java.io.IOException

/**
 * Maps [Throwable] to [AppError].
 * Use the result for logging or to choose user-facing messages (from resources); do not show raw exception text to the user.
 */
class ExceptionToAppErrorMapper {

    fun map(throwable: Throwable): AppError = when (throwable) {
        is ApiExceptionWithStatus -> AppError.ApiError(throwable.statusCode)
        is IOException -> AppError.NetworkError
        else -> AppError.UnknownError(throwable.message)
    }
}
