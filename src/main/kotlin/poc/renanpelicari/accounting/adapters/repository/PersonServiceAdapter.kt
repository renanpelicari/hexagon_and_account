package poc.renanpelicari.accounting.adapters.repository

import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import poc.renanpelicari.accounting.application.domain.Person
import poc.renanpelicari.accounting.ports.repository.PersonRepositoryPort
import java.sql.ResultSet

@Repository
class PersonServiceAdapter(private val jdbcTemplate: NamedParameterJdbcTemplate) : PersonRepositoryPort {

    override fun create(person: Person): Int = jdbcTemplate.update(PersonDb.SQL_INSERT, mapOf(
        Pair("fullname", person.fullname),
        Pair("document", person.document)
    ))

    override fun update(person: Person): Int = jdbcTemplate.update(PersonDb.SQL_UPDATE, mapOf(
        Pair("fullname", person.fullname),
        Pair("document", person.document),
        Pair("id", person.id)
    ))

    override fun findAll(): List<Person> =
        jdbcTemplate.query(PersonDb.SQL_SELECT_ALL, PersonDb.ROW_MAPPER)

    override fun findById(id: Int): Person? = try {
        jdbcTemplate.queryForObject(PersonDb.SQL_SELECT_ONE, mapOf(Pair("id", id)), Person::class.java)
    } catch (ex: DataAccessException) {
        null
    }


    override fun delete(id: Int): Int =
        jdbcTemplate.update(PersonDb.SQL_DELETE_BY_ID, mapOf(Pair("id", id)))
}

open class PersonDb {
    companion object {
        const val COLUMNS = "p.id person_id, p.fullname person_fullname, p.document person_document"
        const val TABLE = "t_person p"
        const val PK = "person_id"

        const val SQL_INSERT = "INSERT INTO $TABLE (fullname, document) VALUES (:fullname, :document)"
        const val SQL_UPDATE = "UPDATE $TABLE SET fullname = :fullname, document = :document WHERE id = :id"
        const val SQL_SELECT_ALL = "SELECT $COLUMNS FROM $TABLE"
        const val SQL_SELECT_ONE = "SELECT $COLUMNS FROM $TABLE WHERE id = :id"
        const val SQL_DELETE_BY_ID = "DELETE FROM $TABLE WHERE id = :id"

        val ROW_MAPPER = RowMapper { rs, _ -> getDbToObjectConverter(rs) }

        fun getDbToObjectConverter(rs: ResultSet) = Person(
            id = rs.getInt(PK),
            fullname = rs.getString("person_fullname"),
            document = rs.getString("person_document")
        )
    }
}
