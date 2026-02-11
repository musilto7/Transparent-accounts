package cz.musilto5.transparentaccounts.common.domain.model

/**
 * Domain identity for a transparent account (id or account number).
 */
@JvmInline
value class AccountId(val value: String) {
    override fun toString(): String = value
}
