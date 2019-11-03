package poc.renanpelicari.accounting.ports.repository

import poc.renanpelicari.accounting.application.domain.Atm

interface AtmRepositoryPort {

    fun create(atm: Atm): Int

    fun findAll(): List<Atm>

    fun delete(id: Int): Int
}
