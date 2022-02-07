package com.example.restfulwebservice.user

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserDaoService {
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

    companion object {
        val users = mutableListOf(
            User(1, "Kenneth", LocalDate.now()),
            User(2, "Alice", LocalDate.now()),
            User(3, "Elena", LocalDate.now()),
        )

        var usersCount = 3
    }
}
