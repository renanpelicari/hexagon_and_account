package poc.renanpelicari.accounting.adapters.repository

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import poc.renanpelicari.accounting.application.domain.Account
import poc.renanpelicari.accounting.application.domain.AtmAccount
import poc.renanpelicari.accounting.application.domain.OrganizationAccount
import poc.renanpelicari.accounting.application.domain.PersonAccount
import poc.renanpelicari.accounting.application.enums.AccountType
import poc.renanpelicari.accounting.ports.repository.AccountRepositoryPort
import java.math.BigDecimal

@Repository
class AccountServiceAdapter(private val jdbcTemplate: NamedParameterJdbcTemplate) : AccountRepositoryPort {

    override fun create(account: Account): Int = jdbcTemplate.update(AccountDb.SQL_INSERT, mapOf(
        Pair("type_id", account.type),
        Pair("currentAmount", account.currentAmount),
        Pair("is_active", account.isActive),
        Pair("is_customer", account.isCustomer),
        Pair("owner_id", account.ownerId)
    ))

    override fun update(account: Account): Int = jdbcTemplate.update(AccountDb.SQL_UPDATE, mapOf(
        Pair("type_id", account.type),
        Pair("is_customer", account.isCustomer),
        Pair("owner_id", account.ownerId)
    ))

    override fun findPersonAccounts(): List<PersonAccount> =
        jdbcTemplate.query(AccountDb.SQL_SELECT_ALL_ACC_TYPE_PERSON, AccountDb.PERSON_ACC_ROW_MAPPER)

    override fun findAtmAccounts(): List<AtmAccount> =
        jdbcTemplate.query(AccountDb.SQL_SELECT_ALL_ACC_TYPE_ATM, AccountDb.ATM_ACC_ROW_MAPPER)


    override fun findOrganizationAccounts(): List<OrganizationAccount> =
        jdbcTemplate.query(AccountDb.SQL_SELECT_ALL_ACC_TYPE_ORG, AccountDb.ORG_ACC_ROW_MAPPER)

    override fun delete(id: Int): Int =
        jdbcTemplate.update(AccountDb.SQL_DELETE_BY_ID, mapOf(Pair("id", id)))

    override fun decreaseAmount(id: Int, quantity: BigDecimal): Int =
        jdbcTemplate.update(AccountDb.SQL_UPDATE_DECREASE_AMOUNT, mapOf(
            Pair("quantity", quantity),
            Pair("owner_id", id)
        ))

    override fun increaseAmount(id: Int, quantity: BigDecimal): Int =
        jdbcTemplate.update(AccountDb.SQL_UPDATE_INCREASE_AMOUNT, mapOf(
            Pair("quantity", quantity),
            Pair("owner_id", id)
        ))

    override fun activate(id: Int): Int =
        jdbcTemplate.update(AccountDb.SQL_UPDATE_ACTIVATE_ACCOUNT, mapOf(Pair("owner_id", id)))

    override fun deactivate(id: Int): Int =
        jdbcTemplate.update(AccountDb.SQL_UPDATE_DEACTIVATE_ACCOUNT, mapOf(Pair("owner_id", id)))
}

open class AccountDb {
    companion object {
        private const val COLUMNS =
            "acc.id acc_id, acc.number acc_number, acc.type_id acc_type_id, acc.current_amount acc_current_amount, " +
                "acc.is_active acc_is_active, acc_is_customer acc_is_customer, acc.owner_id acc_owner_id"

        private const val TABLE = "t_account acc"

        private const val PK = "acc_id"

        const val SQL_INSERT = "INSERT INTO $TABLE (type_id, current_amount, is_active, is_customer, owner_id) " +
            "VALUES(:type_id, :current_amount, :is_active, :is_customer, :owner_id)"

        const val SQL_UPDATE =
            "UPDATE $TABLE SET type_id = :type_id, is_customer = :is_customer, owner_id = :owner_id WHERE id = :id"

        const val SQL_UPDATE_INCREASE_AMOUNT =
            "UPDATE $TABLE SET amount = amount + :quantity WHERE id = :id"

        const val SQL_UPDATE_DECREASE_AMOUNT =
            "UPDATE $TABLE SET amount = amount - :quantity WHERE id = :id"

        const val SQL_UPDATE_ACTIVATE_ACCOUNT =
            "UPDATE $TABLE SET is_active = true WHERE id = :id"

        const val SQL_UPDATE_DEACTIVATE_ACCOUNT =
            "UPDATE $TABLE SET is_active = false WHERE id = :id"

        const val SQL_SELECT_ALL_ACC_TYPE_PERSON = "SELECT $COLUMNS, ${PersonDb.COLUMNS} FROM $TABLE " +
            "INNER JOIN ${PersonDb.TABLE} ON acc_owner_id = ${PersonDb.PK}"

        const val SQL_SELECT_ALL_ACC_TYPE_ATM = "SELECT $COLUMNS, ${AtmDb.COLUMNS} FROM $TABLE " +
            "INNER JOIN ${AtmDb.TABLE} ON acc_owner_id = ${AtmDb.PK}"

        const val SQL_SELECT_ALL_ACC_TYPE_ORG = "SELECT $COLUMNS, ${OrganizationDb.COLUMNS} FROM $TABLE " +
            "INNER JOIN ${OrganizationDb.TABLE} ON acc_owner_id = ${OrganizationDb.PK}"

        const val SQL_DELETE_BY_ID = "DELETE FROM $TABLE WHERE id = :id"

        val PERSON_ACC_ROW_MAPPER = RowMapper { rs, _ ->
            PersonAccount(
                id = rs.getInt(PK),
                number = rs.getInt("acc_number"),
                type = AccountType.getById(rs.getInt("acc_type_id")),
                currentAmount = rs.getBigDecimal("acc_current_amount"),
                isActive = rs.getBoolean("acc_is_active"),
                isCustomer = rs.getBoolean("acc_is_customer"),
                person = PersonDb.getDbToObjectConverter(rs)
            )
        }

        val ATM_ACC_ROW_MAPPER = RowMapper { rs, _ ->
            AtmAccount(
                id = rs.getInt(PK),
                number = rs.getInt("acc_number"),
                type = AccountType.getById(rs.getInt("acc_type_id")),
                currentAmount = rs.getBigDecimal("acc_current_amount"),
                isActive = rs.getBoolean("acc_is_active"),
                isCustomer = rs.getBoolean("acc_is_customer"),
                atm = AtmDb.getDbToObjectConverter(rs)
            )
        }

        val ORG_ACC_ROW_MAPPER = RowMapper { rs, _ ->
            OrganizationAccount(
                id = rs.getInt(PK),
                number = rs.getInt("acc_number"),
                type = AccountType.getById(rs.getInt("acc_type_id")),
                currentAmount = rs.getBigDecimal("acc_current_amount"),
                isActive = rs.getBoolean("acc_is_active"),
                isCustomer = rs.getBoolean("acc_is_customer"),
                organization = OrganizationDb.getDbToObjectConverter(rs)
            )
        }
    }
}
