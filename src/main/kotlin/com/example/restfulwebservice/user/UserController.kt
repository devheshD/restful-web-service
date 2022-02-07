package com.example.restfulwebservice.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userDaoService: UserDaoService
) {
    @GetMapping("/users")
    fun retrieveAllUsers(): List<User> = userDaoService.findAll()

    @GetMapping("/users/{id}")
    fun retrieveUser(
        @PathVariable id: Int
    ): User? = userDaoService.findOne(id)
}
