package poc.renanpelicari.accounting.adapters.repository

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import poc.renanpelicari.accounting.application.domain.Organization
import poc.renanpelicari.accounting.ports.repository.OrganizationRepositoryPort
import java.sql.ResultSet

@Repository
class OrganizationServiceAdapter(private val jdbcTemplate: NamedParameterJdbcTemplate) : OrganizationRepositoryPort {

    override fun create(organization: Organization): Int = jdbcTemplate.update(OrganizationDb.SQL_INSERT, mapOf(
        Pair("name", organization.name),
        Pair("register_code", organization.registerCode)
    ))

    override fun update(organization: Organization): Int = jdbcTemplate.update(OrganizationDb.SQL_UPDATE, mapOf(
        Pair("name", organization.name),
        Pair("register_code", organization.registerCode),
        Pair("id", organization.id)
    ))

    override fun findAll(): List<Organization> =
        jdbcTemplate.query(OrganizationDb.SQL_SELECT_ALL, OrganizationDb.ROW_MAPPER)

    override fun delete(id: Int): Int =
        jdbcTemplate.update(OrganizationDb.SQL_DELETE_BY_ID, mapOf(Pair("id", id)))
}

open class OrganizationDb {
    companion object {
        const val COLUMNS = "o.id org_id, o.name org_name, o.register_code org_register_code"
        const val TABLE = "t_organization o"
        const val PK = "org_id"

        const val SQL_INSERT = "INSERT INTO $TABLE (name, register_code) VALUES (:name, :register_code)"
        const val SQL_UPDATE = "UPDATE $TABLE SET name = :name, register_code = :register_code WHERE id = :id"
        const val SQL_SELECT_ALL = "SELECT $COLUMNS FROM $TABLE"
        const val SQL_DELETE_BY_ID = "DELETE FROM $TABLE WHERE id = :id"

        val ROW_MAPPER = RowMapper { rs, _ -> getDbToObjectConverter(rs) }

        fun getDbToObjectConverter(rs: ResultSet) = Organization(
            id = rs.getInt(PK),
            name = rs.getString("org_name"),
            registerCode = rs.getString("org_register_code")
        )
    }
}
