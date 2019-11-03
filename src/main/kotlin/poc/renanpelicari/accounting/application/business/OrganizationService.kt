package poc.renanpelicari.accounting.application.business

import org.springframework.stereotype.Service
import poc.renanpelicari.accounting.adapters.repository.OrganizationServiceAdapter
import poc.renanpelicari.accounting.application.domain.Organization
import poc.renanpelicari.accounting.application.exceptions.ResourceNotFoundException
import poc.renanpelicari.accounting.dtos.request.CreateOrganizationRequest
import poc.renanpelicari.accounting.dtos.request.UpdateOrganizationRequest
import poc.renanpelicari.accounting.dtos.request.toDomain
import poc.renanpelicari.accounting.dtos.response.OrganizationResponse
import poc.renanpelicari.accounting.dtos.response.toResponse

@Service
class OrganizationService(private val adapter: OrganizationServiceAdapter) {

    fun create(organization: CreateOrganizationRequest) = adapter.create(organization.toDomain()).let { }

    fun update(id: Int, organization: UpdateOrganizationRequest) = findOne(id).let {
        adapter.update(organization.toDomain(it)).let { }
    }

    fun findAll(): List<OrganizationResponse> = adapter.findAll().toResponse()

    fun delete(id: Int) = adapter.delete(id).let { }

    private fun findOne(id: Int): Organization =
        adapter.findById(id) ?: throw ResourceNotFoundException("Organization not found by id $id")
}
