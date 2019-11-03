package poc.renanpelicari.accounting.dtos.request

import poc.renanpelicari.accounting.application.domain.Account
import poc.renanpelicari.accounting.application.enums.AccountType
import java.math.BigDecimal

data class CreateAccountRequest(
    val type: String,
    val currentAmount: BigDecimal?,
    val isActive: Boolean?,
    val isCustomer: Boolean?,
    val ownerId: Int
)

data class UpdateAccountRequest(
    val type: String?,
    val isCustomer: Boolean?,
    val ownerId: Int?
)

internal fun CreateAccountRequest.toDomain() = Account(
    id = null,
    number = null,
    type = AccountType.valueOf(this.type.toUpperCase()),
    currentAmount = this.currentAmount ?: BigDecimal.ZERO,
    isActive = this.isActive ?: true,
    isCustomer = this.isCustomer ?: false,
    ownerId = this.ownerId
)

internal fun UpdateAccountRequest.toDomain(currentAccount: Account) = Account(
    id = currentAccount.id,
    number = currentAccount.number,
    type = getAccountTypeByCode(this.type) ?: currentAccount.type,
    currentAmount = currentAccount.currentAmount,
    isActive = currentAccount.isActive,
    isCustomer = this.isCustomer ?: currentAccount.isCustomer,
    ownerId = this.ownerId ?: currentAccount.ownerId
)

internal fun getAccountTypeByCode(code: String?): AccountType? = when (code.isNullOrEmpty()) {
    true -> null
    else -> AccountType.valueOf(code.toUpperCase())
}
