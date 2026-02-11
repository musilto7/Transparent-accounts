package cz.musilto5.transparentaccounts.data.repository

import cz.musilto5.transparentaccounts.data.datasource.TransparentAccountsDataSource
import cz.musilto5.transparentaccounts.domain.model.AccountId
import cz.musilto5.transparentaccounts.domain.repository.TransparentAccountsRepository
import cz.musilto5.transparentaccounts.model.AccountDetail
import cz.musilto5.transparentaccounts.model.AccountListResponse
import cz.musilto5.transparentaccounts.model.TransactionListResponse
import kotlinx.datetime.LocalDate

class TransparentAccountsRepositoryImpl(
    private val dataSource: TransparentAccountsDataSource
) : TransparentAccountsRepository {

    override suspend fun getAccounts(
        page: Int,
        size: Int,
        filter: String?
    ): AccountListResponse = dataSource.getAccounts(page = page, size = size, filter = filter)

    override suspend fun getAccountDetail(id: AccountId): AccountDetail = dataSource.getAccountDetail(id = id)

    override suspend fun getAccountTransactions(
        id: AccountId,
        page: Int?,
        size: Int?,
        dateFrom: LocalDate?,
        dateTo: LocalDate?
    ): TransactionListResponse = dataSource.getAccountTransactions(
        id = id,
        page = page,
        size = size,
        dateFrom = dateFrom,
        dateTo = dateTo
    )
}
