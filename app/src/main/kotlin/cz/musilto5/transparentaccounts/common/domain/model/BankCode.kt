package cz.musilto5.transparentaccounts.common.domain.model

/**
 * Domain wrapper for bank code.
 */
@JvmInline
value class BankCode(val value: String) {
    override fun toString(): String = value
}
