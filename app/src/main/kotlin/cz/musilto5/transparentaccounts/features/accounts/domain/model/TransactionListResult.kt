package cz.musilto5.transparentaccounts.features.accounts.domain.model

import cz.musilto5.transparentaccounts.common.domain.model.PagingInfo

/**
 * Domain result for paginated transaction list.
 */
data class TransactionListResult(
    val paging: PagingInfo,
    val transactions: List<Transaction>
)
