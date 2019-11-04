package poc.renanpelicari.accounting.application.domain

import poc.renanpelicari.accounting.application.enums.OperationType
import java.math.BigDecimal
import java.time.Instant

data class Transaction(
    val id: Int?,
    val account: Account,
    val operationType: OperationType,
    val amount: BigDecimal,
    val accountReference: Account,
    val transactionReferenceId: Int?,
    val registeredAt: Instant
)

internal fun Transaction.generateReferenceTransaction(transactionId: Int) = Transaction(
    id = null,
    account = this.accountReference,
    accountReference = this.account,
    operationType = OperationType.getOppositeOperation(this.operationType),
    amount = this.amount,
    transactionReferenceId = transactionReferenceId,
    registeredAt = Instant.now()
)
