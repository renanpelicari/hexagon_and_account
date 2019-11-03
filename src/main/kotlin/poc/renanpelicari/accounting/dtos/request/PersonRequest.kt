package poc.renanpelicari.accounting.dtos.request

import poc.renanpelicari.accounting.application.domain.Person

data class CreatePersonRequest(
    val fullname: String,
    val document: String
)

data class UpdatePersonRequest(
    val fullname: String?,
    val document: String?
)

internal fun CreatePersonRequest.toDomain() = Person(
    id = null,
    fullname = this.fullname,
    document = this.document
)

internal fun UpdatePersonRequest.toDomain(currentPerson: Person) = Person(
    id = currentPerson.id,
    fullname = this.fullname ?: currentPerson.fullname,
    document = this.document ?: currentPerson.document
)
