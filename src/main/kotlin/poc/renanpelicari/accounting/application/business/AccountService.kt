package poc.renanpelicari.accounting.application.business

import org.springframework.stereotype.Service
import poc.renanpelicari.accounting.adapters.repository.AccountServiceAdapter
import poc.renanpelicari.accounting.application.domain.Account
import poc.renanpelicari.accounting.application.enums.OperationType
import poc.renanpelicari.accounting.application.exceptions.ResourceNotFoundException
import poc.renanpelicari.accounting.dtos.request.CreateAccountRequest
import poc.renanpelicari.accounting.dtos.request.TransactionRequest
import poc.renanpelicari.accounting.dtos.request.UpdateAccountRequest
import poc.renanpelicari.accounting.dtos.request.toDomain
import poc.renanpelicari.accounting.dtos.response.AccountsResponse
import poc.renanpelicari.accounting.dtos.response.toAtmResponse
import poc.renanpelicari.accounting.dtos.response.toOrganizationResponse
import poc.renanpelicari.accounting.dtos.response.toPersonResponse
import java.math.BigDecimal

@Service
class AccountService(private val adapter: AccountServiceAdapter,
                     private val transactionService: TransactionService) {

    fun create(account: CreateAccountRequest) =
        adapter.create(account.toDomain()).let { }

    fun update(id: Int, account: UpdateAccountRequest) = findOne(id).let {
        adapter.update(account.toDomain(it)).let { }
    }

    fun findAll() = AccountsResponse(
        personAccounts = adapter.findPersonAccounts().toPersonResponse(),
        atmAccounts = adapter.findAtmAccounts().toAtmResponse(),
        organizationAccounts = adapter.findOrganizationAccounts().toOrganizationResponse()
    )

    fun delete(id: Int) = adapter.delete(id).let { }

    fun transfer(id: Int, quantity: BigDecimal, accountRefId: Int) = adapter.decreaseAmount(id, quantity).let {
        adapter.increaseAmount(accountRefId, quantity).let {
            transactionService.register(
                TransactionRequest(
                    accountId = id,
                    operationType = OperationType.TRANSFER_OUT.name,
                    amount = quantity,
                    accountReferenceId = accountRefId
                )
            )
        }
    }

    fun deposit(id: Int, quantity: BigDecimal) = adapter.increaseAmount(id, quantity).let {
        adapter.decreaseAmount(-1, quantity).let {
            transactionService.register(
                TransactionRequest(
                    accountId = id,
                    operationType = OperationType.DEPOSIT_IN.name,
                    amount = quantity,
                    accountReferenceId = -1
                )
            )
        }
    }

    fun withdraw(id: Int, quantity: BigDecimal) = adapter.decreaseAmount(id, quantity).let {
        adapter.increaseAmount(-1, quantity).let {
            transactionService.register(
                TransactionRequest(
                    accountId = id,
                    operationType = OperationType.WITHDRAW_OUT.name,
                    amount = quantity,
                    accountReferenceId = -1
                )
            )
        }
    }

    fun deactivate(id: Int) = adapter.deactivate(id).let { }

    fun activate(id: Int) = adapter.activate(id).let { }

    fun findOne(id: Int): Account =
        adapter.findById(id) ?: throw ResourceNotFoundException("Account not found by id $id")
}
