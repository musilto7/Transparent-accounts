package cz.musilto5.transparentaccounts.features.accounts.data.repository

import cz.musilto5.transparentaccounts.features.accounts.data.datasource.TransparentAccountsDataSource
import cz.musilto5.transparentaccounts.features.accounts.domain.model.AccountId
import cz.musilto5.transparentaccounts.features.accounts.domain.repository.TransparentAccountsRepository
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountDetailDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountListResponseDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.TransactionListResponseDto
import kotlinx.datetime.LocalDate

class TransparentAccountsRepositoryImpl(
    private val dataSource: TransparentAccountsDataSource
) : TransparentAccountsRepository {

    override suspend fun getAccounts(
        page: Int,
        size: Int,
        filter: String?
    ): AccountListResponseDto = dataSource.getAccounts(page = page, size = size, filter = filter)

    override suspend fun getAccountDetail(id: AccountId): AccountDetailDto = dataSource.getAccountDetail(id = id)

    override suspend fun getAccountTransactions(
        id: AccountId,
        page: Int?,
        size: Int?,
        dateFrom: LocalDate?,
        dateTo: LocalDate?
    ): TransactionListResponseDto = dataSource.getAccountTransactions(
        id = id,
        page = page,
        size = size,
        dateFrom = dateFrom,
        dateTo = dateTo
    )
}
