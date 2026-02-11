package cz.musilto5.transparentaccounts.common.domain.model

/**
 * Identifier for a bank account: account number, bank code, and optional prefix.
 */
data class BankAccountIdentifier(
    val accountNumber: AccountNumber,
    val bankCode: BankCode,
    val prefix: String? = null
) {
    override fun toString(): String = buildString {
        if (!prefix.isNullOrBlank()) {
            append(prefix)
            append("-")
        }
        append(accountNumber.value)
        append(" / ")
        append(bankCode.value)
    }
}
