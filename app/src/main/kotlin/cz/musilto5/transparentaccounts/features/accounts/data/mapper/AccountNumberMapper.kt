package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.common.domain.model.AccountNumber

class AccountNumberMapper {
    fun map(value: String?): AccountNumber? = value?.let { AccountNumber(it) }
}
