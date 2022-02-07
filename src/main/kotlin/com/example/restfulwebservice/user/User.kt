package com.example.restfulwebservice.user

import java.time.LocalDate

data class User(
    var id: Int?,
    val name: String,
    val joinDate: LocalDate,
)
