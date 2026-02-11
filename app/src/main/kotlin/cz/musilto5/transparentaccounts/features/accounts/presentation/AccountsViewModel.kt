package cz.musilto5.transparentaccounts.features.accounts.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cz.musilto5.transparentaccounts.features.accounts.data.paging.TransparentAccountsPagingSource
import cz.musilto5.transparentaccounts.features.accounts.domain.model.Account
import cz.musilto5.transparentaccounts.features.accounts.domain.repository.TransparentAccountsRepository
import kotlinx.coroutines.flow.Flow

class AccountsViewModel(
    private val repository: TransparentAccountsRepository
) : ViewModel() {

    private val pageSize = 20

    val accountsFlow: Flow<PagingData<Account>> = Pager(
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
    ).flow
}
