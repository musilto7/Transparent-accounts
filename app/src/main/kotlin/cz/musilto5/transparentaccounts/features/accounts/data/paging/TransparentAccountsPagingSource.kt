package cz.musilto5.transparentaccounts.features.accounts.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cz.musilto5.transparentaccounts.features.accounts.domain.model.Account
import cz.musilto5.transparentaccounts.features.accounts.domain.repository.TransparentAccountsRepository

/**
 * Paging 3 source that loads transparent accounts page by page.
 */
class TransparentAccountsPagingSource(
    private val repository: TransparentAccountsRepository,
    private val pageSize: Int = 20,
    private val filter: String? = null
) : PagingSource<Int, Account>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Account> {
        val page = params.key ?: 0
        return try {
            val result = repository.getAccounts(
                page = page,
                size = params.loadSize.coerceAtLeast(1),
                filter = filter
            )
            val items = result.accounts
            val nextKey = if (items.size < params.loadSize) null else page + 1
            LoadResult.Page(
                data = items,
                prevKey = if (page == 0) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Account>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }
}
