package org.example.repositories

import org.example.model.daos.User
import org.example.model.tables.UserTable
import org.jetbrains.exposed.sql.*
import java.time.Instant

object UserRepository {
    fun create(
        firstName: String,
        lastName: String,
        email: String,
        createdAt: Instant = Instant.now()
    ): Int? {
        return UserTable.insertIgnoreAndGetId {
            it[this.firstName] = firstName
            it[this.lastName] = lastName
            it[this.email] = email
            it[this.createdAt] = createdAt
        }?.value
    }

    fun update(
        id: Int,
        firstName: String,
        lastName: String,
        email: String
    ): Boolean =
        update(Op.build { UserTable.id eq id }, firstName, lastName, email)

    fun update(
        condition: Op<Boolean>,
        firstName: String,
        lastName: String,
        email: String
    ): Boolean {
        return UserTable.update({ condition }) {
            it[UserTable.firstName] = firstName
            it[UserTable.lastName] = lastName
            it[UserTable.email] = email
        } > 0
    }

    fun getById(id: Int): User? = UserTable
        .selectAll().where { UserTable.id eq id }
        .map { User.wrapRow(it) }
        .singleOrNull()

    fun delete(id: Int): Boolean =
        delete(Op.build { UserTable.id eq id })

    fun delete(condition: Op<Boolean>): Boolean =
        UserTable.deleteWhere { condition } > 0
}
