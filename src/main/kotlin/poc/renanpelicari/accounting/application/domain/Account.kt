package poc.renanpelicari.accounting.application.domain

import poc.renanpelicari.accounting.application.enums.AccountType
import java.math.BigDecimal

data class Account(
    val id: Int?,
    val number: Int?,
    val type: AccountType,
    val currentAmount: BigDecimal,
    val isActive: Boolean,
    val isCustomer: Boolean,
    val ownerId: Int
)

data class PersonAccount(
    val id: Int?,
    val number: Int?,
    val type: AccountType,
    val currentAmount: BigDecimal,
    val isActive: Boolean,
    val isCustomer: Boolean,
    val person: Person
)

data class AtmAccount(
    val id: Int?,
    val number: Int?,
    val type: AccountType,
    val currentAmount: BigDecimal,
    val isActive: Boolean,
    val isCustomer: Boolean,
    val atm: Atm
)

data class OrganizationAccount(
    val id: Int?,
    val number: Int?,
    val type: AccountType,
    val currentAmount: BigDecimal,
    val isActive: Boolean,
    val isCustomer: Boolean,
    val organization: Organization
)
