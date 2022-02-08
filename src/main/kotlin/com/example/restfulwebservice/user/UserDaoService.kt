package com.example.restfulwebservice.user

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserDaoService {
    private val log = LoggerFactory.getLogger(javaClass)

    fun findAll(): List<User> = users

    fun save(user: User): User {
        if (user.id == null) {
            user.id = ++usersCount
            users.add(user)
        }

        return user
    }

    fun findOne(id: Int): User? {
        users.forEach { user ->
            if (user.id == id) {
                return user
            }
        }

        return null
    }

    fun deleteById(id: Int): User? {
        val iterator = users.iterator()

        while (iterator.hasNext()) {
            val user = iterator.next()

            if (user.id == id) {
                iterator.remove()
                return user
            }
        }

        return null
    }

    companion object {
        val users = mutableListOf(
            User(1, "Kenneth", LocalDate.now(), "pass1", "707070-1331111"),
            User(2, "Alice", LocalDate.now(), "pass2", "807880-1113311"),
            User(3, "Elena", LocalDate.now(), "pass3", "909070-1112211"),
        )

        var usersCount = 3
    }
}
