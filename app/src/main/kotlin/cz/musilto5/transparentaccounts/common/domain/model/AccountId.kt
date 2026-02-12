package cz.musilto5.transparentaccounts.common.domain.model

import kotlinx.serialization.Serializable

/**
 * Domain identity for a transparent account (id or account number).
 */
@Serializable
@JvmInline
value class AccountId(val value: String) {
    override fun toString(): String = value
}
