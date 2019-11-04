package poc.renanpelicari.accounting.application.business

import org.springframework.stereotype.Service
import poc.renanpelicari.accounting.adapters.repository.AccountServiceAdapter
import poc.renanpelicari.accounting.application.domain.Account
import poc.renanpelicari.accounting.application.exceptions.ResourceNotFoundException
import poc.renanpelicari.accounting.dtos.request.CreateAccountRequest
import poc.renanpelicari.accounting.dtos.request.UpdateAccountRequest
import poc.renanpelicari.accounting.dtos.request.toDomain
import poc.renanpelicari.accounting.dtos.response.AccountsResponse
import poc.renanpelicari.accounting.dtos.response.toAtmResponse
import poc.renanpelicari.accounting.dtos.response.toOrganizationResponse
import poc.renanpelicari.accounting.dtos.response.toPersonResponse
import java.math.BigDecimal

@Service
class AccountService(private val adapter: AccountServiceAdapter) {

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

    fun decreaseAmount(id: Int, quantity: BigDecimal) = adapter.decreaseAmount(id, quantity).let { }

    fun increaseAmount(id: Int, quantity: BigDecimal) = adapter.increaseAmount(id, quantity).let { }

    fun deactivate(id: Int) = adapter.deactivate(id).let { }

    fun activate(id: Int) = adapter.activate(id).let { }

    fun findOne(id: Int): Account =
        adapter.findById(id) ?: throw ResourceNotFoundException("Account not found by id $id")
}
