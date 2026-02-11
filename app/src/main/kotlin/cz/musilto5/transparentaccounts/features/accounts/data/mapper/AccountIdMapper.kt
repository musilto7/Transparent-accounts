package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.common.domain.model.AccountId

class AccountIdMapper {
    fun map(value: String?): AccountId? = value?.let { AccountId(it) }
}
