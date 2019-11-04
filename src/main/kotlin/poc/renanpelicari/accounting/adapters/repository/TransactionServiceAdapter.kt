package poc.renanpelicari.accounting.adapters.repository

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import poc.renanpelicari.accounting.application.domain.Transaction
import poc.renanpelicari.accounting.ports.repository.TransactionRepositoryPort
import java.sql.Date

@Repository
class TransactionServiceAdapter(private val jdbcTemplate: NamedParameterJdbcTemplate) : TransactionRepositoryPort {

    override fun register(transaction: Transaction): Int = jdbcTemplate.update(TransactionDb.SQL_INSERT, mapOf(
        Pair("account_id", transaction.account.id),
        Pair("operation_type_id", transaction.operationType.ordinal),
        Pair("amount", transaction.amount),
        Pair("account_reference_id", transaction.accountReference.id),
        Pair("transaction_reference_id", transaction.transactionReferenceId),
        Pair("registered_at", transaction.registeredAt)
    ))

    override fun isAmountNotBalanced(today: Date): Boolean = jdbcTemplate.queryForObject(
        TransactionDb.SQL_CHECK_AMOUNT_NOT_BALANCE,
        mapOf(Pair("today", today)),
        Boolean::class.java
    ) ?: false

    override fun rollbackTransactions(transactionId: Int, transactionRefId: Int) = jdbcTemplate.update(
        TransactionDb.SQL_ROLLBACK_TRANSACTIONS,
        mapOf(
            Pair("id", transactionId),
            Pair("transaction_reference_id", transactionRefId)
        ))
}

open class TransactionDb {
    companion object {

        private const val TABLE = "t_transaction t"

        const val SQL_INSERT = "INSERT INTO $TABLE (account_id, operation_type_id, amount, account_reference_id, " +
            "transaction_reference_id, registered_at) VALUES (:account_id, :operation_type_id, :amount, " +
            ":account_reference_id, :transaction_reference_id, :registered_at)"

        const val SQL_CHECK_AMOUNT_NOT_BALANCE =
            "SELECT " +
                "(CASE WHEN (SUM(in.amount) - SUM(out.amount)) = 0 THEN FALSE ELSE TRUE END) is_balanced " +
                "FROM t_transaction in, t_transaction_out " +
                "WHERE in.operation_type_id IN (1, 3, 5) " +
                "AND out.operation_type_id IN (2, 4, 6) " +
                "AND DATE(in.registered_at) >= :today " +
                "AND DATE(out.registered_at) >= :today"

        const val SQL_ROLLBACK_TRANSACTIONS =
            "DELETE FROM $TABLE WHERE id IN (:transaction_id, :transaction_reference_id)"
    }
}
