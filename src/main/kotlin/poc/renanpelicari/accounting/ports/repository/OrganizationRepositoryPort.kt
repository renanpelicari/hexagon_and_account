package poc.renanpelicari.accounting.ports.repository

import poc.renanpelicari.accounting.application.domain.Organization

interface OrganizationRepositoryPort {

    fun create(organization: Organization): Int

    fun update(organization: Organization): Int

    fun findAll(): List<Organization>

    fun findById(id: Int): Organization?

    fun delete(id: Int): Int
}
