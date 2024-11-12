package org.example.model.daos

import org.example.model.tables.UserTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(UserTable)

    var firstName by UserTable.firstName
    var lastName by UserTable.lastName
    var email by UserTable.email
    var createdAt by UserTable.createdAt
}
