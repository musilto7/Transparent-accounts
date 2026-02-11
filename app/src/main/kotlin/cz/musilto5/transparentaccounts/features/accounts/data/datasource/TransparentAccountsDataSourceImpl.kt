package cz.musilto5.transparentaccounts.features.accounts.data.datasource

import cz.musilto5.transparentaccounts.features.accounts.domain.model.AccountId
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountDetailDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountListResponseDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.TransactionListResponseDto
import cz.musilto5.transparentaccounts.features.accounts.data.api.DefaultApi
import kotlinx.datetime.LocalDate

class TransparentAccountsDataSourceImpl(
    private val baseUrl: String = "https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v3/transparentAccounts",
    private val apiKey: String
) : TransparentAccountsDataSource {

    private val api: DefaultApi by lazy {
        DefaultApi(baseUrl = baseUrl).apply {
            setApiKey(apiKey)
        }
    }

    override suspend fun getAccounts(
        page: Int,
        size: Int,
        filter: String?
    ): AccountListResponseDto {
        val response = api.getAccounts(page = page, size = size, filter = filter)
        if (!response.success) throw TransparentAccountsApiException(response.status, "getAccounts failed")
        return response.body()
    }

    override suspend fun getAccountDetail(id: AccountId): AccountDetailDto {
        val response = api.getAccountDetail(id = id.value)
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
        val response = api.getAccountTransactions(
            id = id.value,
            page = page,
            size = size,
            dateFrom = dateFrom,
            dateTo = dateTo
        )
        if (!response.success) throw TransparentAccountsApiException(response.status, "getAccountTransactions failed")
        return response.body()
    }
}

class TransparentAccountsApiException(
    val statusCode: Int,
    message: String
) : Exception("API error $statusCode: $message")
