package cz.musilto5.transparentaccounts.data.datasource

import cz.musilto5.transparentaccounts.domain.model.AccountId
import cz.musilto5.transparentaccounts.model.AccountDetail
import cz.musilto5.transparentaccounts.model.AccountListResponse
import cz.musilto5.transparentaccounts.model.TransactionListResponse
import kotlinx.datetime.LocalDate

interface TransparentAccountsDataSource {

    suspend fun getAccounts(
        page: Int = 0,
        size: Int = 10,
        filter: String? = null
    ): AccountListResponse

    suspend fun getAccountDetail(id: AccountId): AccountDetail

    suspend fun getAccountTransactions(
        id: AccountId,
        page: Int? = null,
        size: Int? = null,
        dateFrom: LocalDate? = null,
        dateTo: LocalDate? = null
    ): TransactionListResponse
}
