package poc.renanpelicari.accounting.ports.ui

import org.springframework.web.bind.annotation.*
import poc.renanpelicari.accounting.dtos.request.CreateAccountRequest
import poc.renanpelicari.accounting.dtos.request.UpdateAccountRequest
import poc.renanpelicari.accounting.dtos.response.AccountsResponse
import java.math.BigDecimal

interface AccountUIPort {

    @PostMapping
    fun create(request: CreateAccountRequest)

    @PutMapping("/{id}")
    fun update(request: UpdateAccountRequest, @PathVariable id: Int)

    @GetMapping
    fun findAll(): AccountsResponse

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int)

    @PutMapping("/{id}/amount/decrease/{quantity}")
    fun decreaseAmount(@PathVariable id: Int, @PathVariable quantity: BigDecimal)

    @PutMapping("/{id}/amount/increase/{quantity}")
    fun increaseAmount(@PathVariable id: Int, @PathVariable quantity: BigDecimal)

    @PutMapping("/{id}/activate")
    fun activate(@PathVariable id: Int)

    @PutMapping("/{id}/deactivate")
    fun deactivate(@PathVariable id: Int)
}
