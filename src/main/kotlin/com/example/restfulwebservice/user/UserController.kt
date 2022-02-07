package com.example.restfulwebservice.user

import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userDaoService: UserDaoService
) {
    @GetMapping("/users")
    fun retrieveAllUsers(): List<User> = userDaoService.findAll()

    @GetMapping("/users/{id}")
    fun retrieveUser(@PathVariable id: Int): User? = userDaoService.findOne(id)

    @PostMapping("/users")
    fun createUser(@RequestBody user: User) = userDaoService.save(user)
}
