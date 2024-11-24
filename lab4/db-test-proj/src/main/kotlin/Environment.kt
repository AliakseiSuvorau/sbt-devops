package org.example

data class Environment(
    val postgres_user: String = "postgres",
    val postgres_password: String = "postgres",
    val postgres_driver: String = "org.postgresql.Driver",
    val postgres_url: String = "jdbc:postgresql://postgres:5432/postgres"
)
