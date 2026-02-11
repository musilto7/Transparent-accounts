package cz.musilto5.transparentaccounts.features.accounts.domain.model

/**
 * Domain identity for a transparent account (id or account number).
 */
@JvmInline
value class AccountId(val value: String) {
    override fun toString(): String = value
}
