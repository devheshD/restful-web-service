package com.example.restfulwebservice.user

import com.fasterxml.jackson.databind.ser.FilterProvider
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.fasterxml.jackson.databind.util.BeanUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.http.converter.json.MappingJacksonValue
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AdminUserController(
    private val userDaoService: UserDaoService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/users")
    fun retrieveAllUsers(): MappingJacksonValue {
        //command + alt + v : 변수 생성
        val users = userDaoService.findAll()
        val filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "password")
        val mapping = MappingJacksonValue(users)
        mapping.filters = SimpleFilterProvider().addFilter("UserInfo", filter)

        return mapping
    }

    @GetMapping("/v1/users/{id}")
    fun retrieveUserV1(@PathVariable id: Int): MappingJacksonValue {
        val user = userDaoService.findOne(id) ?: throw UserNotFoundException("ID $id not found")
        val filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn")
        val mapping = MappingJacksonValue(user)
        mapping.filters = SimpleFilterProvider().addFilter("UserInfo", filter)

        return mapping
    }

    @GetMapping("/v2/users/{id}")
    fun retrieveUserV2(@PathVariable id: Int): MappingJacksonValue {
        val user = userDaoService.findOne(id) ?: throw UserNotFoundException("ID $id not found")
        val filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "grade")
        // User -> User2
        val user2 = UserV2(user.id, user.name, user.joinDate, user.password, user.ssn, "VIP")
        val mapping = MappingJacksonValue(user2)
        mapping.filters = SimpleFilterProvider().addFilter("UserInfoV2", filter)

        return mapping
    }
}
