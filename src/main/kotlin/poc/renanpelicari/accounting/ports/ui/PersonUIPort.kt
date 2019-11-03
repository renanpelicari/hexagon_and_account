package poc.renanpelicari.accounting.ports.ui

import org.springframework.web.bind.annotation.*
import poc.renanpelicari.accounting.dtos.request.CreatePersonRequest
import poc.renanpelicari.accounting.dtos.request.UpdatePersonRequest
import poc.renanpelicari.accounting.dtos.response.PersonResponse

interface PersonUIPort {

    @PostMapping
    fun create(request: CreatePersonRequest)

    @PutMapping("/{id}")
    fun update(request: UpdatePersonRequest, @PathVariable id: Int)

    @GetMapping
    fun findAll(): List<PersonResponse>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int)
}
