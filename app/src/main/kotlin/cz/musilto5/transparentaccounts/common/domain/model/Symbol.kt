package cz.musilto5.transparentaccounts.common.domain.model

/**
 * Domain wrapper for payment symbol (variable, constant, or specific symbol).
 */
@JvmInline
value class Symbol(val value: String) {
    override fun toString(): String = value
}
