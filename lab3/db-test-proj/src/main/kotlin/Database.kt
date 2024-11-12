package org.example

import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

fun configureDatabase() {
    val environment = Environment()

    val url = environment.postgres_url
    val driver = environment.postgres_driver
    val user = environment.postgres_user
    val password = environment.postgres_password

    Database.connect(
        url = url,
        driver = driver,
        user = user,
        password = password
    )

    val flyway = Flyway.configure().dataSource(url, user, password).load()
    flyway.migrate()
}
