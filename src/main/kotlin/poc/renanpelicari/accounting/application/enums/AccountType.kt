package poc.renanpelicari.accounting.application.enums

enum class AccountType(id: Int, name: String) {
    ATM(id = 1, name = "ATM"),
    ORG(id = 2, name = "ORG"),
    ONG(id = 3, name = "ONG"),
    PERSON(id = 4, name = "PERSON"),
    COMPANY(id = 5, name = "COMPANY");

    companion object {
        fun getById(id: Int): AccountType = values().first { it.ordinal == id }
    }
}


