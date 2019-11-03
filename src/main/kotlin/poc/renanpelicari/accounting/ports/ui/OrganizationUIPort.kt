package poc.renanpelicari.accounting.ports.ui

import org.springframework.web.bind.annotation.*
import poc.renanpelicari.accounting.dtos.request.CreateOrganizationRequest
import poc.renanpelicari.accounting.dtos.request.UpdateOrganizationRequest
import poc.renanpelicari.accounting.dtos.response.OrganizationResponse

interface OrganizationUIPort {

    @PostMapping
    fun create(request: CreateOrganizationRequest)

    @PutMapping("/{id}")
    fun update(request: UpdateOrganizationRequest, @PathVariable id: Int)

    @GetMapping
    fun findAll(): List<OrganizationResponse>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int)
}
