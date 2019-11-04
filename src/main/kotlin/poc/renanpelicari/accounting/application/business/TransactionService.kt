package poc.renanpelicari.accounting.application.business

import org.springframework.stereotype.Service
import poc.renanpelicari.accounting.adapters.repository.TransactionServiceAdapter
import poc.renanpelicari.accounting.application.domain.Account
import poc.renanpelicari.accounting.application.domain.generateReferenceTransaction
import poc.renanpelicari.accounting.dtos.request.TransactionRequest
import poc.renanpelicari.accounting.dtos.request.toDomain
import java.sql.Date
import java.time.LocalDate

@Service
class TransactionService(private val adapter: TransactionServiceAdapter,
                         private val accountAdapter: AccountService) {

    fun register(request: TransactionRequest) = registerTransactions(
        request = request,
        account = accountAdapter.findOne(request.accountId),
        accountRef = accountAdapter.findOne(request.accountReferenceId)
    ).let { }

    private fun registerTransactions(request: TransactionRequest, account: Account, accountRef: Account) =
        request.toDomain(account, accountRef).let { transaction ->

            adapter.register(transaction).let { transactionId ->

                rollbackTransactionsIfAmountIsNotBalanced(
                    transactionId = transactionId,
                    transactionRefId = adapter.register(transaction.generateReferenceTransaction(transactionId))
                )
            }
        }

    private fun rollbackTransactionsIfAmountIsNotBalanced(transactionId: Int, transactionRefId: Int) =
        takeIf { adapter.isAmountNotBalanced(Date.valueOf(LocalDate.now())) }?.apply {
            adapter.rollbackTransactions(transactionId, transactionRefId).let { }
        }
}
