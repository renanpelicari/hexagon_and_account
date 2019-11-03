package poc.renanpelicari.accounting.application.business

import org.springframework.stereotype.Service
import poc.renanpelicari.accounting.adapters.repository.AtmServiceAdapter
import poc.renanpelicari.accounting.dtos.request.AtmRequest
import poc.renanpelicari.accounting.dtos.request.toDomain
import poc.renanpelicari.accounting.dtos.response.AtmResponse
import poc.renanpelicari.accounting.dtos.response.toResponse

@Service
class AtmService(private val adapter: AtmServiceAdapter) {

    fun create(atm: AtmRequest) = adapter.create(atm.toDomain()).let { }

    fun findAll(): List<AtmResponse> = adapter.findAll().toResponse()

    fun delete(id: Int) = adapter.delete(id).let { }
}
