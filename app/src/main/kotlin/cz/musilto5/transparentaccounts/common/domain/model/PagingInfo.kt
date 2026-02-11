package cz.musilto5.transparentaccounts.common.domain.model

/**
 * Pagination metadata.
 */
data class PagingInfo(
    val pageNumber: Int?,
    val pageSize: Int?,
    val pageCount: Int?,
    val recordCount: Int?
)
