package cz.musilto5.transparentaccounts.features.accounts.presentation

import androidx.lifecycle.ViewModel
import cz.musilto5.transparentaccounts.features.accounts.domain.model.AccountDetail
import cz.musilto5.transparentaccounts.features.accounts.domain.repository.TransparentAccountsRepository
import cz.musilto5.transparentaccounts.features.accounts.presentation.mapper.AccountDetailToViewObjectMapper
import cz.musilto5.transparentaccounts.features.accounts.presentation.model.AccountDetailViewObject

class AccountDetailViewModel(
    private val repository: TransparentAccountsRepository,
    private val accountDetailToViewObjectMapper: AccountDetailToViewObjectMapper
) : ViewModel() {

    fun mapToViewObject(detail: AccountDetail): AccountDetailViewObject =
        accountDetailToViewObjectMapper.map(detail)
}
