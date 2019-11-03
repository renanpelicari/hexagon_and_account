package poc.renanpelicari.accounting.dtos.response

import poc.renanpelicari.accounting.application.domain.AtmAccount
import poc.renanpelicari.accounting.application.domain.OrganizationAccount
import poc.renanpelicari.accounting.application.domain.PersonAccount
import java.math.BigDecimal

data class AccountsResponse(
    val personAccounts: List<PersonAccountResponse>,
    val atmAccounts: List<AtmAccountResponse>,
    val organizationAccounts: List<OrganizationAccountResponse>
)

data class PersonAccountResponse(
    val id: Int,
    val number: Int,
    val type: String,
    val currentAmount: BigDecimal,
    val isActive: Boolean,
    val isCustomer: Boolean,
    val person: PersonResponse
)

data class AtmAccountResponse(
    val id: Int,
    val number: Int,
    val type: String,
    val currentAmount: BigDecimal,
    val isActive: Boolean,
    val isCustomer: Boolean,
    val atm: AtmResponse
)

data class OrganizationAccountResponse(
    val id: Int,
    val number: Int,
    val type: String,
    val currentAmount: BigDecimal,
    val isActive: Boolean,
    val isCustomer: Boolean,
    val organization: OrganizationResponse
)

internal fun PersonAccount.toResponse() = PersonAccountResponse(
    id = this.id!!,
    number = this.number!!,
    type = this.type.name,
    currentAmount = this.currentAmount,
    isActive = this.isActive,
    isCustomer = this.isCustomer,
    person = this.person.toResponse()
)

internal fun AtmAccount.toResponse() = AtmAccountResponse(
    id = this.id!!,
    number = this.number!!,
    type = this.type.name,
    currentAmount = this.currentAmount,
    isActive = this.isActive,
    isCustomer = this.isCustomer,
    atm = this.atm.toResponse()
)

internal fun OrganizationAccount.toResponse() = OrganizationAccountResponse(
    id = this.id!!,
    number = this.number!!,
    type = this.type.name,
    currentAmount = this.currentAmount,
    isActive = this.isActive,
    isCustomer = this.isCustomer,
    organization = this.organization.toResponse()
)
