package poc.renanpelicari.accounting.dtos.response

import poc.renanpelicari.accounting.application.domain.Person

data class PersonResponse(
    val id: Int,
    val fullname: String,
    val document: String
)

internal fun Person.toResponse() = PersonResponse(
    id = this.id!!,
    fullname = this.fullname,
    document = this.document
)

internal fun List<Person>.toResponse() = this.map { it.toResponse() }
