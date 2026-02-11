package cz.musilto5.transparentaccounts.features.accounts.presentation.mapper

import cz.musilto5.transparentaccounts.common.presentation.orPlaceholder
import cz.musilto5.transparentaccounts.features.accounts.domain.model.Account
import cz.musilto5.transparentaccounts.features.accounts.presentation.model.AccountViewObject

class AccountToViewObjectMapper {

    fun map(account: Account): AccountViewObject = AccountViewObject(
        name = account.name.orPlaceholder(),
        bankAccount = account.bankAccount?.toString().orPlaceholder(),
        currency = account.currency?.toString().orPlaceholder()
    )
}
