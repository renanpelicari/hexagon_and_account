package poc.renanpelicari.accounting.dtos.response

import poc.renanpelicari.accounting.application.domain.Atm

data class AtmResponse(
    val id: Int,
    val serialNumber: String
)

internal fun Atm.toResponse() = AtmResponse(
    id = this.id!!,
    serialNumber = this.serialNumber
)
