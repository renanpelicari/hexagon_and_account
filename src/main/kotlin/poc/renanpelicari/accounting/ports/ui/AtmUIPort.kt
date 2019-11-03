package poc.renanpelicari.accounting.ports.ui

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import poc.renanpelicari.accounting.dtos.request.AtmRequest
import poc.renanpelicari.accounting.dtos.response.AtmResponse

interface AtmUIPort {

    @PostMapping
    fun create(request: AtmRequest)

    @GetMapping
    fun findAll(): List<AtmResponse>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int)
}
