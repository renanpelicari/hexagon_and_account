package poc.renanpelicari.accounting.dtos.response

import poc.renanpelicari.accounting.application.domain.Organization

data class OrganizationResponse(
    val id: Int,
    val name: String,
    val registerCode: String
)

internal fun Organization.toResponse() = OrganizationResponse(
    id = this.id!!,
    name = this.name,
    registerCode = this.registerCode
)

internal fun List<Organization>.toResponse() = this.map { it.toResponse() }
