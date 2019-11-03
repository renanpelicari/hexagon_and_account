package poc.renanpelicari.accounting.adapters.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import poc.renanpelicari.accounting.application.business.OrganizationService
import poc.renanpelicari.accounting.dtos.request.CreateOrganizationRequest
import poc.renanpelicari.accounting.dtos.request.UpdateOrganizationRequest
import poc.renanpelicari.accounting.dtos.response.OrganizationResponse
import poc.renanpelicari.accounting.ports.ui.OrganizationUIPort

@RestController
@RequestMapping("/api/v1/organization")
class OrganizationControllerAdapter(private val organizationService: OrganizationService) : OrganizationUIPort {

    override fun create(request: CreateOrganizationRequest) = organizationService.create(request)

    override fun update(request: UpdateOrganizationRequest, id: Int) = organizationService.update(id, request)

    override fun findAll(): List<OrganizationResponse> = organizationService.findAll()

    override fun delete(id: Int) = organizationService.delete(id)
}
