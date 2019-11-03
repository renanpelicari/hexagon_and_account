package poc.renanpelicari.accounting.ports.repository

import poc.renanpelicari.accounting.application.domain.Account
import poc.renanpelicari.accounting.application.domain.AtmAccount
import poc.renanpelicari.accounting.application.domain.OrganizationAccount
import poc.renanpelicari.accounting.application.domain.PersonAccount
import java.math.BigDecimal

interface AccountRepositoryPort {

    fun create(account: Account): Int

    fun update(account: Account): Int

    fun findPersonAccounts(): List<PersonAccount>

    fun findAtmAccounts(): List<AtmAccount>

    fun findOrganizationAccounts(): List<OrganizationAccount>

    fun delete(id: Int): Int

    fun decreaseAmount(id: Int, quantity: BigDecimal): Int

    fun increaseAmount(id: Int, quantity: BigDecimal): Int

    fun activate(id: Int): Int

    fun deactivate(id: Int): Int
}
