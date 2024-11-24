package repositories

import org.example.configureDatabase
import org.example.connectToDatabase
import org.example.repositories.UserRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.Test

class UserRepositoryTest {
    @BeforeEach
    fun initTest() {
        connectToDatabase()
    }

    @Test
    fun testCreate() {
        assertEquals(1, transaction { UserRepository.create("Alex", "Suvorov", "suvorov.as@gmail.com") })
    }

    @Test
    fun testUpdate() {
        val newUserId = transaction { UserRepository.create("Alex", "Suvorov", "suvorov.as@gmail.com") }
        assertNotNull(newUserId)
        assertTrue(transaction { UserRepository.update(newUserId!!, "Alexey", "Suvorov", "suvorov.as@gmail.com") })
    }

    @Test
    fun testGetById() {
        val newUserId = transaction { UserRepository.create("Alex", "Suvorov", "suvorov.as@gmail.com") }

        val user = assertDoesNotThrow { transaction { UserRepository.getById(newUserId!!) } }
        assertNotNull(user)
        assertEquals(user!!.firstName, "Alex")
        assertEquals(user.lastName, "Suvorov")
        assertEquals(user.email, "suvorov.as@gmail.com")
    }

    @Test
    fun testDelete() {
        val newUserId = transaction { UserRepository.create("Alex", "Suvorov", "suvorov.as@gmail.com") }

        assertNotNull(newUserId)
        val deleted = assertDoesNotThrow { transaction { UserRepository.delete(newUserId!!) } }
        assertTrue(deleted)
    }
}