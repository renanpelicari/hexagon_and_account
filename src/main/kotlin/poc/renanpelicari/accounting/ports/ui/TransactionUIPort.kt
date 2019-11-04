package poc.renanpelicari.accounting.ports.ui

import org.springframework.web.bind.annotation.PostMapping
import poc.renanpelicari.accounting.dtos.request.TransactionRequest

interface TransactionUIPort {

    @PostMapping
    fun register(request: TransactionRequest)
}
