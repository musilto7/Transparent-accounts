package cz.musilto5.transparentaccounts.presentation.accounts

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cz.musilto5.transparentaccounts.data.paging.TransparentAccountsPagingSource
import cz.musilto5.transparentaccounts.domain.repository.TransparentAccountsRepository
import cz.musilto5.transparentaccounts.model.AccountReference
import kotlinx.coroutines.flow.Flow

class AccountsViewModel(
    private val repository: TransparentAccountsRepository
) : ViewModel() {

    private val pageSize = 20

    val accountsFlow: Flow<PagingData<AccountReference>> = Pager(
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
