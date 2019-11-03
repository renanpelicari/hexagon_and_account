package poc.renanpelicari.accounting.dtos.request

import poc.renanpelicari.accounting.application.domain.Atm

data class AtmRequest(
    val serialNumber: String
)

internal fun AtmRequest.toDomain() = Atm(
    id = null,
    serialNumber = this.serialNumber
)
