package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.common.domain.model.PagingInfo

internal class PagingInfoMapper {
    fun map(
        pageNumber: Int?,
        pageSize: Int?,
        pageCount: Int?,
        recordCount: Int?
    ): PagingInfo = PagingInfo(
        pageNumber = pageNumber,
        pageSize = pageSize,
        pageCount = pageCount,
        recordCount = recordCount
    )
}
