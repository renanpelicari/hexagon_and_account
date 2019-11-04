package poc.renanpelicari.accounting.dtos.request

import poc.renanpelicari.accounting.application.domain.Account
import poc.renanpelicari.accounting.application.domain.Transaction
import poc.renanpelicari.accounting.application.enums.OperationType
import java.math.BigDecimal
import java.time.Instant

data class TransactionRequest(
    val accountId: Int,
    val operationType: String,
    val amount: BigDecimal,
    val accountReferenceId: Int
)

internal fun TransactionRequest.toDomain(account: Account, accountReference: Account) = Transaction(
    id = null,
    account = account,
    operationType = OperationType.valueOf(this.operationType),
    amount = this.amount,
    accountReference = accountReference,
    transactionReferenceId = null,
    registeredAt = Instant.now()
)
