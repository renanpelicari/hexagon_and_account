package poc.renanpelicari.accounting.ports.repository

import poc.renanpelicari.accounting.application.domain.Person

interface PersonRepositoryPort {

    fun create(person: Person): Int

    fun update(person: Person): Int

    fun findAll(): List<Person>

    fun findById(id: Int): Person?

    fun delete(id: Int): Int
}
