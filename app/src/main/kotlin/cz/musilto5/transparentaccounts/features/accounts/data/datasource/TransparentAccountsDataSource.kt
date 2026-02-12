package cz.musilto5.transparentaccounts.features.accounts.data.datasource

import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountDetailDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountListResponseDto

internal interface TransparentAccountsDataSource {

    suspend fun getAccounts(
        page: Int = 0,
        size: Int = 10,
        filter: String? = null
    ): AccountListResponseDto

    suspend fun getAccountDetail(id: AccountId): AccountDetailDto

}
