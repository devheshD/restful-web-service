package com.example.restfulwebservice.user

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class UserController(
    private val userDaoService: UserDaoService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/users")
    fun retrieveAllUsers(): List<User> = userDaoService.findAll()

    @GetMapping("/users/{id}")
    fun retrieveUser(@PathVariable id: Int): User? =
        userDaoService.findOne(id) ?: throw UserNotFoundExceptIon("ID $id not found")

    @PostMapping("/users")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val savedUser = userDaoService.save(user)

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.id)
            .toUri()

        return ResponseEntity.created(location).build()
    }
}
