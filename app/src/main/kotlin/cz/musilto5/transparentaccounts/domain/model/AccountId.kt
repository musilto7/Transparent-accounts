package cz.musilto5.transparentaccounts.domain.model

/**
 * Domain identity for a transparent account (id or account number).
 */
@JvmInline
value class AccountId(val value: String) {
    override fun toString(): String = value
}
