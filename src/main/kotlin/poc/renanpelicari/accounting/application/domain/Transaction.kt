package poc.renanpelicari.accounting.application.domain

import java.math.BigDecimal
import java.time.Instant

data class Transaction(
    val id: Int?,
    val account: Account,
    val operationType: OperationType,
    val amount: BigDecimal,
    val accountReference: Account,
    val transactionReference: Transaction?,
    val registeredAt: Instant
)
