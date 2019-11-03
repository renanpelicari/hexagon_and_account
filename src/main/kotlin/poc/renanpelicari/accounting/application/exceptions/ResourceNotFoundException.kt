package poc.renanpelicari.accounting.application.exceptions

class ResourceNotFoundException : RuntimeException {
    constructor() : super("Resource not Found")

    constructor(msg: String) : super(msg)
}
