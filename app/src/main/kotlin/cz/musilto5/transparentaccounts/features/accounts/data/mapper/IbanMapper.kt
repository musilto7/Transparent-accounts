package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.common.domain.model.Iban

internal class IbanMapper {
    fun map(value: String?): Iban? = value?.let { Iban(it) }
}
