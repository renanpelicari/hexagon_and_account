package poc.renanpelicari.accounting.dtos.request

import poc.renanpelicari.accounting.application.domain.Organization

data class CreateOrganizationRequest(
    val name: String,
    val registerCode: String
)

data class UpdateOrganizationRequest(
    val name: String?,
    val registerCode: String?
)

internal fun CreateOrganizationRequest.toDomain() = Organization(
    id = null,
    name = this.name,
    registerCode = this.registerCode
)

internal fun UpdateOrganizationRequest.toDomain(currentOrganization: Organization) = Organization(
    id = currentOrganization.id,
    name = this.name ?: currentOrganization.name,
    registerCode = this.registerCode ?: currentOrganization.registerCode
)
