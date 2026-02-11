package cz.musilto5.transparentaccounts.features.accounts.domain.model

import cz.musilto5.transparentaccounts.common.domain.model.PagingInfo

/**
 * Domain result for paginated account list.
 */
data class AccountListResult(
    val paging: PagingInfo,
    val accounts: List<Account>
)
