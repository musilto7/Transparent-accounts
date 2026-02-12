package cz.musilto5.transparentaccounts.features.accounts.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cz.musilto5.transparentaccounts.common.domain.model.PagingInfo
import cz.musilto5.transparentaccounts.features.accounts.domain.model.Account
import cz.musilto5.transparentaccounts.features.accounts.domain.repository.TransparentAccountsRepository

/**
 * Paging 3 source that loads transparent accounts page by page.
 * Always requests [ACCOUNTS_PAGE_SIZE] from the API (Paging may ask for more for prefetch).
 */
internal class TransparentAccountsPagingSource(
    private val repository: TransparentAccountsRepository,
    private val filter: String? = null
) : PagingSource<Int, Account>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Account> {
        val page = params.key ?: 0
        // force page size 50
        val pageSize = ACCOUNTS_PAGE_SIZE
        return try {
            val result = repository.getAccounts(
                page = page,
                size = pageSize,
                filter = filter
            )
            val items = result.accounts
            val nextKey = nextPageKey(
                currentPage = page,
                paging = result.paging,
                itemsSize = items.size,
                loadSize = pageSize
            )
            LoadResult.Page(
                data = items,
                prevKey = if (page == 0) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    /**
     * Next page key (0-based, matches API page parameter).
     * Stop when response is empty or partial, or when API pageCount says no more pages.
     */
    private fun nextPageKey(
        currentPage: Int,
        paging: PagingInfo,
        itemsSize: Int,
        loadSize: Int
    ): Int? {
        if (itemsSize == 0) return null
        if (itemsSize < loadSize) return null
        val pageCount = paging.pageCount
        return if (pageCount != null && pageCount > 0 && currentPage + 1 >= pageCount) null
        else currentPage + 1
    }

    override fun getRefreshKey(state: PagingState<Int, Account>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }
}
