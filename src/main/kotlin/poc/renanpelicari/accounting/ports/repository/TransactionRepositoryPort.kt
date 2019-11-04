package poc.renanpelicari.accounting.ports.repository

import poc.renanpelicari.accounting.application.domain.Transaction
import java.sql.Date

interface TransactionRepositoryPort {

    fun register(transaction: Transaction): Int

    fun isAmountNotBalanced(today: Date): Boolean

    fun rollbackTransactions(transactionId: Int, transactionRefId: Int): Int
}
