package poc.renanpelicari.accounting.adapters.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import poc.renanpelicari.accounting.application.business.TransactionService
import poc.renanpelicari.accounting.dtos.request.TransactionRequest
import poc.renanpelicari.accounting.ports.ui.TransactionUIPort

@RestController
@RequestMapping("/api/v1/transactions")
class TransactionControllerAdapter(private val transactionService: TransactionService) : TransactionUIPort {

    override fun register(request: TransactionRequest) = transactionService.register(request)
}
