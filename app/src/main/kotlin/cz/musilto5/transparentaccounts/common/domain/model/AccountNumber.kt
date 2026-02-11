package cz.musilto5.transparentaccounts.common.domain.model

/**
 * Domain wrapper for account number.
 */
@JvmInline
value class AccountNumber(val value: String) {
    override fun toString(): String = value
}
