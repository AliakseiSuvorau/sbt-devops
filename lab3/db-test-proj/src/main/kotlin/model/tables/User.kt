package org.example.model.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object UserTable : IntIdTable("users", "user_id") {
    val firstName = text("first_name")
    val lastName = text("last_name")
    val email = text("email")
    val createdAt = timestamp("created_at")
}