package poc.renanpelicari.accounting.adapters.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import poc.renanpelicari.accounting.application.business.AccountService
import poc.renanpelicari.accounting.dtos.request.CreateAccountRequest
import poc.renanpelicari.accounting.dtos.request.UpdateAccountRequest
import poc.renanpelicari.accounting.dtos.response.AccountsResponse
import poc.renanpelicari.accounting.ports.ui.AccountUIPort
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/account")
class AccountControllerAdapter(private val accountService: AccountService) : AccountUIPort {

    override fun create(request: CreateAccountRequest) = accountService.create(request)

    override fun update(request: UpdateAccountRequest, id: Int) = accountService.update(id, request)

    override fun findAll(): AccountsResponse = accountService.findAll()

    override fun delete(id: Int) = accountService.delete(id)

    override fun decreaseAmount(id: Int, quantity: BigDecimal) = accountService.decreaseAmount(id, quantity)

    override fun increaseAmount(id: Int, quantity: BigDecimal) = accountService.increaseAmount(id, quantity)

    override fun activate(id: Int) = accountService.activate(id)

    override fun deactivate(id: Int) = accountService.deactivate(id)

}
