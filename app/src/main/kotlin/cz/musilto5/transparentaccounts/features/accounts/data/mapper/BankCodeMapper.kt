package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.common.domain.model.BankCode

class BankCodeMapper {
    fun map(value: String?): BankCode? = value?.let { BankCode(it) }
}
