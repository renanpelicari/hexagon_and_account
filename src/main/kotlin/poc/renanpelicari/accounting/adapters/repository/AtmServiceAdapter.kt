package poc.renanpelicari.accounting.adapters.repository

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import poc.renanpelicari.accounting.application.domain.Atm
import poc.renanpelicari.accounting.ports.repository.AtmRepositoryPort
import java.sql.ResultSet

@Repository
class AtmServiceAdapter(private val jdbcTemplate: NamedParameterJdbcTemplate) : AtmRepositoryPort {

    override fun create(atm: Atm): Int = jdbcTemplate.update(AtmDb.SQL_INSERT, mapOf(
        Pair("document", atm.serialNumber)
    ))

    override fun findAll(): List<Atm> =
        jdbcTemplate.query(AtmDb.SQL_SELECT_ALL, AtmDb.ROW_MAPPER)

    override fun delete(id: Int): Int =
        jdbcTemplate.update(AtmDb.SQL_DELETE_BY_ID, mapOf(Pair("id", id)))
}

open class AtmDb {
    companion object {

        const val COLUMNS = "a.id atm_id, a.serial_number atm_serial_number"
        const val TABLE = "t_atm a"
        const val PK = "atm_id"

        const val SQL_INSERT = "INSERT INTO $TABLE (serial_number) VALUES (:serial_number)"
        const val SQL_SELECT_ALL = "SELECT $COLUMNS FROM $TABLE"
        const val SQL_DELETE_BY_ID = "DELETE FROM $TABLE WHERE id = :id"

        val ROW_MAPPER = RowMapper { rs, _ -> getDbToObjectConverter(rs) }

        fun getDbToObjectConverter(rs: ResultSet) = Atm(
            id = rs.getInt(PK),
            serialNumber = rs.getString("atm_serial_number")
        )
    }
}
