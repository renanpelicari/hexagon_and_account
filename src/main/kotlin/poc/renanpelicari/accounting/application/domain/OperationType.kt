package poc.renanpelicari.accounting.application.domain

enum class OperationType(id: Int, code: String) {
    TRANSFER_IN(1, "transfer_in"),
    TRANSFER_OUT(2, "transfer_out"),
    DEPOSIT_IN(3, "deposit_in"),
    DEPOSIT_OUT(4, "deposit_out"),
    WITHDRAW_IN(5, "withdraw_in"),
    WITHDRAW_OUT(6, "withdraw_out")
}
