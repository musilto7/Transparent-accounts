package cz.musilto5.transparentaccounts.features.accounts.data.repository

import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.features.accounts.data.datasource.TransparentAccountsDataSource
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.AccountDtoMapper
import cz.musilto5.transparentaccounts.features.accounts.data.mapper.TransactionDtoMapper
import cz.musilto5.transparentaccounts.features.accounts.domain.model.AccountDetail
import cz.musilto5.transparentaccounts.features.accounts.domain.model.AccountListResult
import cz.musilto5.transparentaccounts.features.accounts.domain.model.TransactionListResult
import cz.musilto5.transparentaccounts.features.accounts.domain.repository.TransparentAccountsRepository
import kotlinx.datetime.LocalDate

class TransparentAccountsRepositoryImpl(
    private val dataSource: TransparentAccountsDataSource,
    private val accountDtoMapper: AccountDtoMapper,
    private val transactionDtoMapper: TransactionDtoMapper
) : TransparentAccountsRepository {

    override suspend fun getAccounts(
        page: Int,
        size: Int,
        filter: String?
    ): AccountListResult = accountDtoMapper.mapToAccountListResult(
        dataSource.getAccounts(page = page, size = size, filter = filter)
    )

    override suspend fun getAccountDetail(id: AccountId): AccountDetail = accountDtoMapper.mapToAccountDetail(
        dataSource.getAccountDetail(id = id)
    )

    override suspend fun getAccountTransactions(
        id: AccountId,
        page: Int?,
        size: Int?,
        dateFrom: LocalDate?,
        dateTo: LocalDate?
    ): TransactionListResult = transactionDtoMapper.mapToTransactionListResult(
        dataSource.getAccountTransactions(
            id = id,
            page = page,
            size = size,
            dateFrom = dateFrom,
            dateTo = dateTo
        )
    )
}
