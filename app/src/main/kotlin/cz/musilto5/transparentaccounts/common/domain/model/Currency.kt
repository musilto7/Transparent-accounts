package cz.musilto5.transparentaccounts.common.domain.model

/**
 * Domain wrapper for currency code.
 */
@JvmInline
value class Currency(val value: String) {
    override fun toString(): String = value
}
