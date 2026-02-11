package cz.musilto5.transparentaccounts.common.domain.model

/**
 * Amount in a given currency.
 */
data class Money(
    val currency: Currency,
    val amount: Double
)
