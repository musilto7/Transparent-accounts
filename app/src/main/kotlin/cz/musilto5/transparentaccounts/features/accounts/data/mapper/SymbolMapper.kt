package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.common.domain.model.Symbol

class SymbolMapper {
    fun map(value: String?): Symbol? = value?.let { Symbol(it) }
}
