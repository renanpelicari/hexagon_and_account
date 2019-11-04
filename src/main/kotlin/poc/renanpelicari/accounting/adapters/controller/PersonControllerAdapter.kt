package poc.renanpelicari.accounting.adapters.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import poc.renanpelicari.accounting.application.business.PersonService
import poc.renanpelicari.accounting.dtos.request.CreatePersonRequest
import poc.renanpelicari.accounting.dtos.request.UpdatePersonRequest
import poc.renanpelicari.accounting.dtos.response.PersonResponse
import poc.renanpelicari.accounting.ports.ui.PersonUIPort

@RestController
@RequestMapping("/api/v1/people")
class PersonControllerAdapter(private val personService: PersonService) : PersonUIPort {

    override fun create(request: CreatePersonRequest) = personService.create(request)

    override fun update(request: UpdatePersonRequest, id: Int) = personService.update(id, request)

    override fun findAll(): List<PersonResponse> = personService.findAll()

    override fun delete(id: Int) = personService.delete(id)
}
