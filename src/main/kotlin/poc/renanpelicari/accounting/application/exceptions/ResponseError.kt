package poc.renanpelicari.accounting.application.exceptions

import java.time.LocalDateTime

data class ResponseError(
    val status: Int,
    val error: String,
    val message: MutableList<String>,
    val timestamp: LocalDateTime
) {
    constructor(
        status: Int,
        error: String,
        message: String,
        timestamp: LocalDateTime
    ) : this(
        status = status,
        error = error,
        message = listOf<String>(message).toMutableList(),
        timestamp = timestamp
    )
    
    fun add(error: String) {
        message.run { add(error) }
    }
}
