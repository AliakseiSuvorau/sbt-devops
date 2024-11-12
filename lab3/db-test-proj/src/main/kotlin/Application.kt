package org.example

import org.example.repositories.UserRepository
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    configureDatabase()

    // Let's add new user
    val id = transaction {
        UserRepository.create(
            firstName = "Aliaksei",
            lastName = "Suvorau",
            email = "suvorau.aliaksei@gmail.com"
        )
    }

    println(if (id != null) "User was created" else "User was not created")
}