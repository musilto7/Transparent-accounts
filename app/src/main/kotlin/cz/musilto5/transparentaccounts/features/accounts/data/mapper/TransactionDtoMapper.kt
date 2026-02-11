package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import cz.musilto5.transparentaccounts.features.accounts.data.dto.TransactionDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.TransactionListResponseDto
import cz.musilto5.transparentaccounts.features.accounts.data.dto.TransactionSenderDto
import cz.musilto5.transparentaccounts.features.accounts.domain.model.Transaction
import cz.musilto5.transparentaccounts.features.accounts.domain.model.TransactionListResult
import cz.musilto5.transparentaccounts.features.accounts.domain.model.TransactionSender

internal class TransactionDtoMapper(
    private val bankAccountIdentifierMapper: BankAccountIdentifierMapper,
    private val moneyMapper: MoneyMapper,
    private val symbolMapper: SymbolMapper,
    private val pagingInfoMapper: PagingInfoMapper
) {

    fun mapToTransaction(dto: TransactionDto): Transaction = Transaction(
        amount = dto.amount?.let { moneyMapper.map(it.currency, it.value?.toDouble()) },
        processingDate = dto.processingDate,
        description = dto.description,
        sender = dto.sender?.let { mapToTransactionSender(it) },
        variableSymbol = symbolMapper.map(dto.variableSymbol),
        constantSymbol = symbolMapper.map(dto.constantSymbol),
        specificSymbol = symbolMapper.map(dto.specificSymbol)
    )

    fun mapToTransactionSender(dto: TransactionSenderDto): TransactionSender = TransactionSender(
        name = dto.name,
        bankAccount = bankAccountIdentifierMapper.map(dto.accountNumber, dto.bankCode)
    )

    fun mapToTransactionListResult(dto: TransactionListResponseDto): TransactionListResult = TransactionListResult(
        paging = pagingInfoMapper.map(
            dto.pageNumber,
            dto.pageSize,
            dto.pageCount,
            dto.recordCount
        ),
        transactions = dto.transactions?.map { mapToTransaction(it) } ?: emptyList()
    )
}
