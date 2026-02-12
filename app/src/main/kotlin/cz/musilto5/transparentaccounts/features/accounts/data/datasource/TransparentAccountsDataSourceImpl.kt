package cz.musilto5.transparentaccounts.features.accounts.data.datasource

import android.util.Log
import cz.musilto5.transparentaccounts.common.domain.error.ApiExceptionWithStatus
import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountDetailDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountListResponseDto
import cz.musilto5.transparentaccounts.features.accounts.data.api.DefaultApi

internal class TransparentAccountsDataSourceImpl(
    private val api: DefaultApi,
) : TransparentAccountsDataSource {

    override suspend fun getAccounts(
        page: Int,
        size: Int,
        filter: String?,
    ): AccountListResponseDto {
        Log.d(TAG, "getAccounts(page=$page, size=$size, filter=$filter)")
        val response = api.getAccounts(page = page, size = size, filter = filter)
        Log.d(TAG, "getAccounts() -> HTTP ${response.status}")
        if (!response.success) throw TransparentAccountsApiException(response.status, "getAccounts failed")
        val body = response.body()
        Log.d(TAG, "getAccounts() body: ${body.accounts?.size ?: 0} accounts, pageCount=${body.pageCount}")
        return body
    }

    override suspend fun getAccountDetail(id: AccountId): AccountDetailDto {
        Log.d(TAG, "getAccountDetail(id=${id.value})")
        val response = api.getAccountDetail(id = id.value)
        Log.d(TAG, "getAccountDetail() -> HTTP ${response.status}")
        if (!response.success) throw TransparentAccountsApiException(response.status, "getAccountDetail failed")
        return response.body()
    }

    private companion object {

        const val TAG = "TransparentAccountsApi"
    }
}

internal class TransparentAccountsApiException(
    override val statusCode: Int,
    message: String,
) : Exception("API error $statusCode: $message"), ApiExceptionWithStatus
