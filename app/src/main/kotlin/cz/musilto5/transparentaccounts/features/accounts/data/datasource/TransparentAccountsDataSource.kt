package cz.musilto5.transparentaccounts.features.accounts.data.datasource

import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountDetailDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountListResponseDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.TransactionListResponseDto
import kotlinx.datetime.LocalDate

internal interface TransparentAccountsDataSource {

    suspend fun getAccounts(
        page: Int = 0,
        size: Int = 10,
        filter: String? = null
    ): AccountListResponseDto

    suspend fun getAccountDetail(id: AccountId): AccountDetailDto

    suspend fun getAccountTransactions(
        id: AccountId,
        page: Int? = null,
        size: Int? = null,
        dateFrom: LocalDate? = null,
        dateTo: LocalDate? = null
    ): TransactionListResponseDto
}
