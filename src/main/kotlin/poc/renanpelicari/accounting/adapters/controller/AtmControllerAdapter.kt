package poc.renanpelicari.accounting.adapters.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import poc.renanpelicari.accounting.application.business.AtmService
import poc.renanpelicari.accounting.dtos.request.AtmRequest
import poc.renanpelicari.accounting.dtos.response.AtmResponse
import poc.renanpelicari.accounting.ports.ui.AtmUIPort

@RestController
@RequestMapping("/api/v1/atm")
class AtmControllerAdapter(private val atmService: AtmService) : AtmUIPort {

    override fun create(request: AtmRequest) = atmService.create(request)

    override fun findAll(): List<AtmResponse> = atmService.findAll()

    override fun delete(id: Int) = atmService.delete(id)
}
