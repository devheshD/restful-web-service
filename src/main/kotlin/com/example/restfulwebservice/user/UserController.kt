package com.example.restfulwebservice.user

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
class UserController(
    private val userDaoService: UserDaoService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/users")
    fun retrieveAllUsers(): List<User> = userDaoService.findAll()

    @GetMapping("/users/{id}")
    fun retrieveUser(@PathVariable id: Int): User? =
        userDaoService.findOne(id) ?: throw UserNotFoundException("ID $id not found")

    @PostMapping("/users")
    fun createUser(@Valid @RequestBody user: User): ResponseEntity<User> {
        val savedUser = userDaoService.save(user)

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.id)
            .toUri()

        return ResponseEntity.created(location).build()
    }

    @DeleteMapping("/users/{id}")
    fun deleteUsers(@PathVariable id: Int): User? =
        userDaoService.deleteById(id) ?: throw UserNotFoundException("ID $id not found")
}
