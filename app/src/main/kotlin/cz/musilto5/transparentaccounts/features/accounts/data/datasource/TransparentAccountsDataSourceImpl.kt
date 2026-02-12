package cz.musilto5.transparentaccounts.features.accounts.data.datasource

import android.util.Log
import cz.musilto5.transparentaccounts.common.domain.error.ApiExceptionWithStatus
import cz.musilto5.transparentaccounts.common.domain.model.AccountId
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountDetailDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountListResponseDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.TransactionListResponseDto
import cz.musilto5.transparentaccounts.features.accounts.data.api.DefaultApi
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate

internal class TransparentAccountsDataSourceImpl(
    private val api: DefaultApi
) : TransparentAccountsDataSource {

    override suspend fun getAccounts(
        page: Int,
        size: Int,
        filter: String?
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
        val idForApi = idForDetailApi(id)
        Log.d(TAG, "getAccountDetail(id=$idForApi)")
        val response = api.getAccountDetail(id = idForApi)
        Log.d(TAG, "getAccountDetail() -> HTTP ${response.status}")
        if (!response.success) throw TransparentAccountsApiException(response.status, "getAccountDetail failed")
        return response.body()
    }

    override suspend fun getAccountTransactions(
        id: AccountId,
        page: Int?,
        size: Int?,
        dateFrom: LocalDate?,
        dateTo: LocalDate?
    ): TransactionListResponseDto {
        val idForApi = idForDetailApi(id)
        Log.d(TAG, "getAccountTransactions(id=$idForApi, page=$page, size=$size, dateFrom=$dateFrom, dateTo=$dateTo)")
        val response = api.getAccountTransactions(
            id = idForApi,
            page = page,
            size = size,
            dateFrom = dateFrom,
            dateTo = dateTo
        )
        Log.d(TAG, "getAccountTransactions() -> HTTP ${response.status}")
        if (!response.success) throw TransparentAccountsApiException(response.status, "getAccountTransactions failed")
        val body = response.body()
        Log.d(TAG, "getAccountTransactions() body: ${body.transactions?.size ?: 0} transactions")
        return body
    }

    /**
     * ID for account detail/transactions API: strip bank code (e.g. "123 / 0800" → "123").
     * API expects account id or account number only, not "number / bankCode".
     */
    private fun idForDetailApi(id: AccountId): String =
        id.value.substringBefore(" / ").trim().ifEmpty { id.value }

    private companion object {
        const val TAG = "TransparentAccountsApi"
    }
}

internal class TransparentAccountsApiException(
    override val statusCode: Int,
    message: String
) : Exception("API error $statusCode: $message"), ApiExceptionWithStatus
