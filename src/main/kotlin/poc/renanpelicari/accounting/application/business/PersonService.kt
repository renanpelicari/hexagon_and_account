package poc.renanpelicari.accounting.application.business

import org.springframework.stereotype.Service
import poc.renanpelicari.accounting.adapters.repository.PersonServiceAdapter
import poc.renanpelicari.accounting.application.domain.Person
import poc.renanpelicari.accounting.application.exceptions.ResourceNotFoundException
import poc.renanpelicari.accounting.dtos.request.CreatePersonRequest
import poc.renanpelicari.accounting.dtos.request.UpdatePersonRequest
import poc.renanpelicari.accounting.dtos.request.toDomain
import poc.renanpelicari.accounting.dtos.response.PersonResponse
import poc.renanpelicari.accounting.dtos.response.toResponse


@Service
class PersonService(private val adapter: PersonServiceAdapter) {

    fun create(person: CreatePersonRequest) =
        adapter.create(person.toDomain()).let { }

    fun update(id: Int, person: UpdatePersonRequest) = findOne(id).let {
        adapter.update(person.toDomain(it)).let { }
    }

    fun findAll(): List<PersonResponse> = adapter.findAll().toResponse()

    fun delete(id: Int) = adapter.delete(id).let { }

    private fun findOne(id: Int): Person =
        adapter.findById(id) ?: throw ResourceNotFoundException("Person not found by id $id")

}
