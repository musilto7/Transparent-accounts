package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountDetailDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountListResponseDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.AccountReferenceDto
import cz.musilto5.transparentaccounts.features.accounts.domain.model.Account
import cz.musilto5.transparentaccounts.features.accounts.domain.model.AccountDetail
import cz.musilto5.transparentaccounts.features.accounts.domain.model.AccountListResult

internal class AccountDtoMapper(
    private val accountIdMapper: AccountIdMapper,
    private val bankAccountIdentifierMapper: BankAccountIdentifierMapper,
    private val currencyMapper: CurrencyMapper,
    private val moneyMapper: MoneyMapper,
    private val ibanMapper: IbanMapper,
    private val pagingInfoMapper: PagingInfoMapper
) {

    fun mapToAccount(dto: AccountReferenceDto): Account = Account(
        id = accountIdMapper.map(dto.id),
        bankAccount = bankAccountIdentifierMapper.map(dto.accountNumber, dto.bankCode),
        name = dto.name,
        currency = currencyMapper.map(dto.currency)
    )

    fun mapToAccountListResult(dto: AccountListResponseDto): AccountListResult = AccountListResult(
        paging = pagingInfoMapper.map(
            dto.pageNumber,
            dto.pageSize,
            dto.pageCount,
            dto.recordCount
        ),
        accounts = dto.accounts?.map { mapToAccount(it) } ?: emptyList()
    )

    fun mapToAccountDetail(dto: AccountDetailDto): AccountDetail = AccountDetail(
        id = accountIdMapper.map(dto.id),
        bankAccount = bankAccountIdentifierMapper.map(dto.accountNumber, dto.bankCode),
        name = dto.name,
        balance = moneyMapper.map(dto.currency, dto.balance?.toDouble()),
        iban = ibanMapper.map(dto.iban),
        description = dto.description,
        note = dto.note
    )
}
