package poc.renanpelicari.accounting.application.enums

enum class OperationType(id: Int, name: String) {
    TRANSFER_IN(id = 1, name = "transfer_in"),
    TRANSFER_OUT(id = 2, name = "transfer_out"),
    DEPOSIT_IN(id = 3, name = "deposit_in"),
    DEPOSIT_OUT(id = 4, name = "deposit_out"),
    WITHDRAW_IN(id = 5, name = "withdraw_in"),
    WITHDRAW_OUT(id = 6, name = "withdraw_out");

    companion object {
        fun getOppositeOperation(operationType: OperationType): OperationType = when (operationType) {
            TRANSFER_IN -> TRANSFER_OUT
            TRANSFER_OUT -> TRANSFER_IN
            DEPOSIT_IN -> DEPOSIT_OUT
            DEPOSIT_OUT -> DEPOSIT_IN
            WITHDRAW_IN -> WITHDRAW_OUT
            WITHDRAW_OUT -> WITHDRAW_IN
        }
    }
}
