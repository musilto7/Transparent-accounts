package cz.musilto5.transparentaccounts.features.accounts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import cz.musilto5.transparentaccounts.features.accounts.data.paging.TransparentAccountsPagingSource
import cz.musilto5.transparentaccounts.features.accounts.domain.repository.TransparentAccountsRepository
import cz.musilto5.transparentaccounts.features.accounts.presentation.mapper.AccountToViewObjectMapper
import cz.musilto5.transparentaccounts.features.accounts.presentation.model.AccountViewObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountsViewModel(
    private val repository: TransparentAccountsRepository,
    private val accountToViewObjectMapper: AccountToViewObjectMapper
) : ViewModel() {

    private val pageSize = 20

    val accountsFlow: Flow<PagingData<AccountViewObject>> = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            TransparentAccountsPagingSource(
                repository = repository,
                pageSize = pageSize,
                filter = null
            )
        }
    ).flow.map { pagingData -> pagingData.map { accountToViewObjectMapper.map(it) } }
}
