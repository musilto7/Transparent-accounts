package cz.musilto5.transparentaccounts.common.domain.model

/**
 * Domain wrapper for IBAN (International Bank Account Number).
 */
@JvmInline
value class Iban(val value: String) {
    override fun toString(): String = value
}
