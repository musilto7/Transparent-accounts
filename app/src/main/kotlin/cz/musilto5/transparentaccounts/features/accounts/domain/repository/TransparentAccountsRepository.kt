package cz.musilto5.transparentaccounts.features.accounts.domain.repository

import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.features.accounts.domain.model.AccountDetail
import cz.musilto5.transparentaccounts.features.accounts.domain.model.AccountListResult
import cz.musilto5.transparentaccounts.features.accounts.domain.model.TransactionListResult
import kotlinx.datetime.LocalDate

/**
 * Domain contract for transparent accounts data.
 * Implemented in the data layer; UI/ViewModel should depend on this interface only.
 */
interface TransparentAccountsRepository {

    suspend fun getAccounts(
        page: Int = 0,
        size: Int = 10,
        filter: String? = null
    ): AccountListResult

    suspend fun getAccountDetail(id: AccountId): AccountDetail

    suspend fun getAccountTransactions(
        id: AccountId,
        page: Int? = null,
        size: Int? = null,
        dateFrom: LocalDate? = null,
        dateTo: LocalDate? = null
    ): TransactionListResult
}
